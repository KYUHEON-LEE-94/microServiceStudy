# Function to start a container if it is not running
function Start-ContainerIfNotRunning {
    param (
        [string]$ContainerName,
        [string]$Image,
        [string]$PortMapping,
        [string]$EnvVars
    )

    $containerRunning = docker ps -q -f name=$ContainerName
    $containerExited = docker ps -aq -f status=exited -f name=$ContainerName

    if ($containerRunning) {
        Write-Output "$ContainerName is already running."
    } elseif ($containerExited) {
        Write-Output "$ContainerName exists but is not running. Starting it..."
        docker start $ContainerName
    } else {
        Write-Output "$ContainerName does not exist. Creating and starting it..."
        docker run -d --name $ContainerName $PortMapping $EnvVars $Image
    }
}

# Start Zipkin container
Start-ContainerIfNotRunning -ContainerName "zipkinCon" -Image "openzipkin/zipkin" -PortMapping "-p 9411:9411" -EnvVars ""

# Start Keycloak container
Start-ContainerIfNotRunning -ContainerName "keycloakCon" -Image "quay.io/keycloak/keycloak:25.0.1" -PortMapping "-p 8080:8080" -EnvVars "-e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin"

# Start MongoDB container
Start-ContainerIfNotRunning -ContainerName "mongo" -Image "mongo" -PortMapping "-p 27017:27017" -EnvVars "-e MONGO_INITDB_DATABASE=product-service"

# Start MySQL container for order-service
Start-ContainerIfNotRunning -ContainerName "mysql-container" -Image "mysql:latest" -PortMapping "-p 3306:3306" -EnvVars "-e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=oder-service"

# Start MySQL container for inventory-service
Start-ContainerIfNotRunning -ContainerName "mysql-container2" -Image "mysql:latest" -PortMapping "-p 3307:3306" -EnvVars "-e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=inventory-service"

# Check if Docker Compose containers are running and start them if not
$composeRunning = docker-compose ps -q

if ($composeRunning) {
    Write-Output "Docker Compose containers are already running."
} else {
    Write-Output "Starting Docker Compose containers..."
    docker-compose up -d
}

Write-Output "All containers started successfully."

# 실행 명령어
# .\run_docker_containers.ps1
