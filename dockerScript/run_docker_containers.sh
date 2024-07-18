# Bash 스크립트를 사용하여 Docker 컨테이너 실행

# Zipkin 컨테이너 실행
echo "Starting Zipkin container..."
docker run -d --name zipkinCon -p 9411:9411 openzipkin/zipkin

# keycloak
echo "Starting Keycloak container..."
docker run -d --name keycloakCon -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.1 start-dev

# MongoDB 컨테이너 실행
echo "Starting MongoDB container..."
docker run -d --name mongo -p 27017:27017 -e MONGO_INITDB_DATABASE=product-service mongo

# MySQL 컨테이너 실행 (oder-service)
echo "Starting MySQL container for order-service..."
docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=oder-service -p 3306:3306 -d mysql:latest

# MySQL 컨테이너 실행 (inventory-service)
echo "Starting MySQL container2 for inventory-service..."
docker run -d --name mysql-container2 -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=inventory-service -p 3307:3306 -d mysql:latest

echo "All containers started successfully."