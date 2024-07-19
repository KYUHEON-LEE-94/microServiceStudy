#!/bin/bash

# Function to start a container if it is not running
start_container_if_not_running() {
    CONTAINER_NAME=$1
    IMAGE=$2
    PORT_MAPPING=$3
    ENV_VARS=$4

    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        echo "$CONTAINER_NAME is already running."
    elif [ "$(docker ps -aq -f status=exited -f name=$CONTAINER_NAME)" ]; then
        echo "$CONTAINER_NAME exists but is not running. Starting it..."
        docker start $CONTAINER_NAME
    else
        echo "$CONTAINER_NAME does not exist. Creating and starting it..."
        docker run -d --name $CONTAINER_NAME $PORT_MAPPING $ENV_VARS $IMAGE
    fi
}

# Start Zipkin container
start_container_if_not_running "zipkinCon" "openzipkin/zipkin" "-p 9411:9411" ""

# Start Keycloak container
start_container_if_not_running "keycloakCon" "quay.io/keycloak/keycloak:25.0.1" "-p 8080:8080" "-e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin"

# Start MongoDB container
start_container_if_not_running "mongo" "mongo" "-p 27017:27017" "-e MONGO_INITDB_DATABASE=product-service"

# Start MySQL container for order-service
start_container_if_not_running "mysql-container" "mysql:latest" "-p 3306:3306" "-e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=oder-service"

# Start MySQL container for inventory-service
start_container_if_not_running "mysql-container2" "mysql:latest" "-p 3307:3306" "-e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=inventory-service"

# Check if Docker Compose containers are running and start them if not
if [ "$(docker-compose ps -q)" ]; then
    echo "Docker Compose containers are already running."
else
    echo "Starting Docker Compose containers..."
    docker-compose up -d
fi

echo "All containers started successfully."
