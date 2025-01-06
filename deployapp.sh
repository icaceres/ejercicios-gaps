#!/bin/bash

# Activar modo estricto para detener la ejecución en caso de error
set -e

# Función para imprimir mensajes con formato
log() {
  echo -e "\e[1;34m[INFO]\e[0m $1"
}

error_exit() {
  echo -e "\e[1;31m[ERROR]\e[0m $1"
  exit 1
}

# Validar parámetros de entrada
if [ -z "$1" ] || [ -z "$2" ]; then
  error_exit "Uso: ./redeploy.sh <APP_NAME> <CODE_PATH> [MANIFEST_PATH]"
fi

# Parámetros y valores predeterminados
APP_NAME="$1"
CODE_PATH="$2"
MANIFEST_PATH="${3:-/home/ishmael/Documentos/ejercicios-gaps/k8s-manifests/}"
DOCKER_IMAGE=$APP_NAME

# Validar rutas
log "Validando rutas..."
if [ ! -d "$CODE_PATH" ]; then
  error_exit "El directorio del código ($CODE_PATH) no existe."
fi

if [ ! -d "$MANIFEST_PATH" ]; then
  error_exit "El directorio de manifiestos ($MANIFEST_PATH) no existe."
fi

if [ ! -f "$MANIFEST_PATH/$APP_NAME.yaml" ]; then
  error_exit "El archivo de manifiesto ($MANIFEST_PATH/$APP_NAME.yaml) no existe."
fi

# Cambiar al directorio del código
log "Cambiando al directorio del código..."
cd "$CODE_PATH"

# Construir la aplicación
log "Construyendo la aplicación..."
if ! ./gradlew build; then
  error_exit "Error al construir la aplicación con Gradle."
fi

# Eliminar el deployment existente
log "Eliminando deployment existente..."
if kubectl get deployment $APP_NAME &>/dev/null; then
  kubectl delete deployment $APP_NAME || error_exit "No se pudo eliminar el deployment."
else
  log "No se encontró un deployment existente para $APP_NAME."
fi

# Eliminar el servicio existente
log "Eliminando servicio existente..."
if kubectl get service $APP_NAME &>/dev/null; then
  kubectl delete service $APP_NAME || error_exit "No se pudo eliminar el servicio."
else
  log "No se encontró un servicio existente para $APP_NAME."
fi

# Detener y eliminar contenedores que usan la imagen
log "Deteniendo y eliminando contenedores que usan la imagen..."
if docker ps -a --filter ancestor=$DOCKER_IMAGE --format "{{.ID}}" | grep -q .; then
  docker ps -a --filter ancestor=$DOCKER_IMAGE --format "{{.ID}}" | xargs -r docker stop
  docker ps -a --filter ancestor=$DOCKER_IMAGE --format "{{.ID}}" | xargs -r docker rm
else
  log "No se encontraron contenedores que usen la imagen $DOCKER_IMAGE."
fi

# Esperar a que los pods sean eliminados
log "Esperando 5 segundos para asegurar la destrucción de los pods en Minikube..."
sleep 5

# Eliminar la imagen de Minikube
log "Eliminando imagen de Minikube..."
if minikube image ls | grep -q $DOCKER_IMAGE; then
  if ! minikube image rm $DOCKER_IMAGE; then
    error_exit "No se pudo eliminar la imagen de Minikube."
  fi
else
  log "La imagen $DOCKER_IMAGE no estaba en Minikube."
fi

# Construir la nueva imagen Docker
log "Construyendo nueva imagen Docker..."
if ! docker build -t $DOCKER_IMAGE .; then
  error_exit "Error al construir la imagen Docker."
fi

# Cargar la imagen en Minikube
log "Cargando imagen en Minikube..."
if ! minikube image load $DOCKER_IMAGE; then
  error_exit "Error al cargar la imagen en Minikube."
fi

# Aplicar el manifiesto de Kubernetes
log "Aplicando manifiesto de Kubernetes..."
if ! kubectl apply -f "$MANIFEST_PATH/$APP_NAME.yaml"; then
  error_exit "Error al aplicar el manifiesto de Kubernetes."
fi

# Finalización exitosa
log "¡Despliegue completado exitosamente!"

