import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = false
jar.enabled = true

plugins {
    val kotlinVersion = "1.6.20"
    val springBootVersion = "2.6.6"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.jpa") version kotlinVersion
}

val springCloudVersion: String by rootProject

dependencies {
    api(project(":infra-rds"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}
