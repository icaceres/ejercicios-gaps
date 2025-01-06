#!/bin/bash

# Actualizar el sistema
sudo apt update && sudo apt upgrade -y

# Instalar dependencias necesarias
sudo apt install -y apt-transport-https software-properties-common wget gpg

# Agregar la clave GPG de Elastic
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo gpg --dearmor -o /usr/share/keyrings/elasticsearch-keyring.gpg

# Agregar el repositorio de Elastic
echo "deb [signed-by=/usr/share/keyrings/elasticsearch-keyring.gpg] https://artifacts.elastic.co/packages/8.x/apt stable main" | sudo tee /etc/apt/sources.list.d/elastic-8.x.list

# Actualizar lista de paquetes
sudo apt update

# Instalar Elasticsearch
sudo apt install -y elasticsearch

# Configurar Elasticsearch
sudo cat > /etc/elasticsearch/elasticsearch.yml << EOF
cluster.name: mi-elk-local
node.name: node-1
network.host: localhost
http.port: 9200
discovery.type: single-node
xpack.security.enabled: false
EOF

# Iniciar y habilitar Elasticsearch
sudo systemctl daemon-reload
sudo systemctl enable elasticsearch
sudo systemctl start elasticsearch

# Instalar Kibana
sudo apt install -y kibana

# Configurar Kibana
sudo cat > /etc/kibana/kibana.yml << EOF
server.port: 5601
server.host: "localhost"
elasticsearch.hosts: ["http://localhost:9200"]
EOF

# Iniciar y habilitar Kibana
sudo systemctl enable kibana
sudo systemctl start kibana

# Instalar Logstash
sudo apt install -y logstash

# Crear una configuración básica de Logstash para microservicios
sudo cat > /etc/logstash/conf.d/microservices.conf << EOF
input {
  tcp {
    port => 5044
    codec => json
  }
}

output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "microservice-logs-%{+YYYY.MM.dd}"
  }
}
EOF

# Iniciar y habilitar Logstash
sudo systemctl enable logstash
sudo systemctl start logstash

# Verificar el estado de los servicios
echo "Verificando el estado de los servicios..."
sudo systemctl status elasticsearch
sudo systemctl status kibana
sudo systemctl status logstash

echo "Instalación completada. Accede a Kibana en http://localhost:5601"
