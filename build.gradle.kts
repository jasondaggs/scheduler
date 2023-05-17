

plugins {
    id("org.springframework.boot") version "2.7.1" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.8.20"
    kotlin("jvm") version "1.8.20"
}
val javaTargetVersion = JavaVersion.VERSION_17
val kotestVersion = "5.5.4"

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
            implementation("io.arrow-kt:arrow-core:1.1.3")
            implementation(project(":common"))
            implementation(project(":platform"))

            runtimeOnly("com.h2database:h2")
            runtimeOnly("org.springframework.boot:spring-boot-devtools")

            testImplementation("org.springframework.boot:spring-boot-starter-test") {
                exclude(module = "mockito-core")
                exclude(module = "mockito-junit-jupiter")
            }
            testImplementation("org.springframework.boot:spring-boot-test")
            testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") {
                exclude(group = "junit", module = "junit")
            }
            testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
            testImplementation("com.ninja-squad:springmockk:4.0.2") {
                exclude(group = "junit", module = "junit")
            }
            testImplementation(kotlin("test"))
            //testImplementation(kotlin("it"))

        }
subprojects {
    repositories {
        mavenCentral()
    }
}

tasks.test {
    useJUnitPlatform()
}
