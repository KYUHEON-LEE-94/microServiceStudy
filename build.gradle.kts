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
}

jib {
    from {
        image = "eclipse-temurin:17.0.4.1_1-jre"
    }
    to {
        image = "maizurzu/${project.name}"
        tags = setOf("latest")
    }
    container {
        mainClass = "com.study.${project.name}.MainKt"
        jvmFlags = listOf(
            "-Xms512m",
            "-Xmx1024m"
        )
        ports = listOf("8080")
    }
}
// docker login
// ./gradlew jib

