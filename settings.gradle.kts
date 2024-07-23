pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "microservice"
include(":discovery-server", ":api-gateway", ":order-service", ":inventory-service", ":product", ":notification-service")


