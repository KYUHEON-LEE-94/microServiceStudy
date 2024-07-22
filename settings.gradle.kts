pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

}
rootProject.name = "microservice"
include(":order-service", ":inventory-service", ":product", ":discovery-server", ":api-gateway", ":notification-service")

