pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

}
rootProject.name = "microService"
include(":order-service", ":inventory-service", ":product")

