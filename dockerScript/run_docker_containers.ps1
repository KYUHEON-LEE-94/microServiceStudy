# PowerShell 스크립트를 사용하여 Docker 컨테이너 실행 또는 시작

# Function to start or create a Docker container
function Start-OrCreateContainer {
    param (
        [string]$containerName,
        [string]$runCommand
    )

    $container = docker ps -a -q -f name=$containerName
    if ($container) {
        Write-Output "Starting existing container: $containerName..."
        docker start $containerName
    } else {
        Write-Output "Creating and starting new container: $containerName..."
        Invoke-Expression $runCommand
    }
}

# Zipkin 컨테이너 실행 또는 생성
Start-OrCreateContainer -containerName "zipkinCon" -runCommand "docker run -d --name zipkinCon -p 9411:9411 openzipkin/zipkin"

# Keycloak 컨테이너 실행 또는 생성
Start-OrCreateContainer -containerName "keycloakCon" -runCommand "docker run -d --name keycloakCon -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.1 start-dev"

# MongoDB 컨테이너 실행 또는 생성
Start-OrCreateContainer -containerName "mongo" -runCommand "docker run -d --name mongo -p 27017:27017 -e MONGO_INITDB_DATABASE=product-service mongo"

# MySQL 컨테이너 실행 또는 생성 (order-service)
Start-OrCreateContainer -containerName "mysql-container" -runCommand "docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=oder-service -p 3306:3306 mysql:latest"

# MySQL 컨테이너 실행 또는 생성 (inventory-service)
Start-OrCreateContainer -containerName "mysql-container2" -runCommand "docker run -d --name mysql-container2 -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=inventory-service -p 3307:3306 mysql:latest"

Write-Output "All containers started or created successfully."

# 실행 명령어
# .\run_docker_containers.ps1
