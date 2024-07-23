import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.24"
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("com.google.cloud.tools.jib") version "3.3.1"
}


extra["springCloudVersion"] = "2023.0.3"

allprojects {
    apply(plugin = "com.google.cloud.tools.jib")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "org.example"
    version = "1.0-SNAPSHOT"

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }


    repositories {
        mavenCentral()
        maven("https://repo.spring.io/release")
    }
}

sourceSets {
    test {
        kotlin {
            srcDir("src/test/kotlin")
        }
        resources {
            srcDir("src/test/resources")
        }
    }
}

tasks.named("jib") {
    enabled = false
}

subprojects {

    extra["springCloudVersion"] = "2023.0.3"

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("io.micrometer:micrometer-tracing-bridge-brave")
        implementation("io.zipkin.reporter2:zipkin-reporter-brave")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }


    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    jib {
        from {
            image = "eclipse-temurin:17.0.4.1_1-jre"
        }
        to {
            image = "docker.io/maizurzu/${project.name}"
            // set DOCKER_USERNAME=your-docker-username
            tags = setOf("latest", project.version.toString())
//            credHelper {
//                helper = "wincred" // Docker Hub credential helper 설정
//            }
        }
        container {
            jvmFlags = listOf("-Xms512m", "-Xmx1024m")
            mainClass = "com.study.${project.name}.Application" // 각 서브 프로젝트에 맞는 메인 클래스 이름으로 변경 필요
        }
    }
}

project(":api-gateway") {
    jib {
        container {
            mainClass = "com.study.apigateway.ApiGatewayApplication"
        }
    }
}

project(":discovery-server") {
    jib {
        container {
            mainClass = "com.study.discoveryserver.DiscoveryServerApplication"
        }
    }
}

project(":inventory-service") {
    jib {
        container {
            mainClass = "com.study.inventoryservice.InventoryServiceApplication"
        }
    }
}

project(":notification-service") {
    jib {
        container {
            mainClass = "com.study.notificationservice.NotificationServiceApplication"
        }
    }
}

project(":order-service") {
    jib {
        container {
            mainClass = "com.study.orderservice.OrderServiceApplication"
        }
    }
}

project(":product") {
    jib {
        container {
            mainClass = "com.study.product.ProductApplication"
        }
    }
}

tasks.register("jibDockerBuildAll") {
    dependsOn(subprojects.map { it.tasks.named("jibDockerBuild") })
}
// ./gradlew jibDockerBuildAll