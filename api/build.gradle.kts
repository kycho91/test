val jar: Jar by tasks
jar.enabled = false

plugins {
    val kotlinVersion = "1.6.20"
    val springBootVersion = "2.6.6"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    id ("com.google.cloud.tools.jib") version "3.2.1"
}

val springCloudVersion: String by rootProject

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(project(":api-common"))
    implementation(project(":domain"))

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

sourceSets {
    create("integration-test") {
        kotlin {
            compileClasspath += main.get().output + configurations.testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }
    }
}

jib {
    from {
        image = "532333860892.dkr.ecr.ap-northeast-2.amazonaws.com/jdk11:latest"
        platforms {
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }
    to {
        image = "${project.name}-${project.version.toString().toLowerCase()}"
        tags = setOf("latest")
    }
    container {
        creationTime = "USE_CURRENT_TIMESTAMP"
        jvmFlags = listOf(
            "-Dspring.profiles.active=local",
            "-XX:+UseContainerSupport",
            "-Dserver.port=8080",
            "-Dfile.encoding=UTF-8"
        )
        ports = listOf("8080")
        appRoot = "/inara/app"
        user = "inara"
    }
}
