#!/bin/bash

# Function to start or create a Docker container
start_or_create_container() {
    local container_name=$1
    local run_command=$2

    if [ "$(docker ps -a -q -f name=${container_name})" ]; then
        echo "Starting existing container: ${container_name}..."
        docker start ${container_name}
    else
        echo "Creating and starting new container: ${container_name}..."
        eval ${run_command}
    fi
}

# Zipkin 컨테이너 실행 또는 생성
start_or_create_container "zipkinCon" "docker run -d --name zipkinCon -p 9411:9411 openzipkin/zipkin"

# Keycloak 컨테이너 실행 또는 생성
start_or_create_container "keycloakCon" "docker run -d --name keycloakCon -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.1 start-dev"

# MongoDB 컨테이너 실행 또는 생성
start_or_create_container "mongo" "docker run -d --name mongo -p 27017:27017 -e MONGO_INITDB_DATABASE=product-service mongo"

# MySQL 컨테이너 실행 또는 생성 (order-service)
start_or_create_container "mysql-container" "docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=oder-service -p 3306:3306 mysql:latest"

# MySQL 컨테이너 실행 또는 생성 (inventory-service)
start_or_create_container "mysql-container2" "docker run -d --name mysql-container2 -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=inventory-service -p 3307:3306 mysql:latest"
