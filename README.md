# Ejercicios para Reducir GAPs

## Descripción

Este repositorio contiene una serie de ejercicios diseñados para identificar y reducir las brechas (GAPs) en diversas áreas del desarrollo de software. Los ejercicios abarcan temas como microservicios, seguridad, despliegue en Kubernetes, entre otros.

## Estructura del Proyecto

La estructura del repositorio es la siguiente:

```bash
ejercicios-gaps/
├── brk-msa-notification/
├── example-poo/
├── inv-msa-product/
├── k8s-manifests/
├── sec-msa-gateway/
├── sle-msa-order/
├── deployapp.sh
├── install-elk.sh
├── ejercicio-bp.drawio.png
└── README.md

```

### Directorios

- **brk-msa-notification/**: Ejercicio relacionado con notificaciones en una arquitectura de microservicios.
- **example-poo/**: Ejemplos de Programación Orientada a Objetos.
- **inv-msa-product/**: Microservicio de gestión de productos para un sistema de inventario.
- **k8s-manifests/**: Manifiestos de Kubernetes para despliegue de aplicaciones.
- **sec-msa-gateway/**: Puerta de enlace (gateway) con consideraciones de seguridad en microservicios.
- **sle-msa-order/**: Microservicio de gestión de órdenes.

### Archivos

- **deployapp.sh**: Script para desplegar aplicaciones.
- **install-elk.sh**: Script para instalar la pila ELK (Elasticsearch, Logstash y Kibana).
- **ejercicio-bp.drawio.png**: Diagrama de procesos en formato PNG.

## Tecnologías Utilizadas

- **Java**: Lenguaje principal para el desarrollo de los ejercicios.
- **Kubernetes**: Orquestador de contenedores para el despliegue de aplicaciones.
- **ELK Stack**: Conjunto de herramientas para la gestión y visualización de logs.

## Instalación y Uso

### Clonar el repositorio:

```bash
git clone https://github.com/icaceres/ejercicios-gaps.git
cd ejercicios-gaps
```

### Revisar cada ejercicio:
Cada carpeta contiene instrucciones específicas en su propio archivo `README.md` o en comentarios dentro del código fuente.

### Scripts de ayuda:

- **deployapp.sh**: Utiliza este script para desplegar las aplicaciones en tu entorno local o en un clúster de Kubernetes.
- **install-elk.sh**: Ejecuta este script para instalar la pila ELK en tu entorno.

Asegúrate de otorgar permisos de ejecución a los scripts:

```bash
chmod +x deployapp.sh install-elk.sh
```

Luego, puedes ejecutarlos con:

```bash
./deployapp.sh <APP_NAME> <CODE_PATH> [MANIFEST_PATH]
./install-elk.sh
```



