import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "com.electrodna"
version = "1.0-SNAPSHOT"

val javaTargetVersion = JavaVersion.VERSION_17
val kotestVersion = "5.5.4"

tasks.test {
    useJUnitPlatform()
}

//sourceSets {
//    test {
//        kotlin {
//            srcDir("src/it/kotlin")
//            srcDir("src/test/kotlin")
//        }
//    }
//}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.arrow-kt:arrow-core:1.1.3")
    implementation(project(":common"))

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(module = "mockito-junit-jupiter")
    }
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") {
        exclude(group = "junit" ,module ="junit")
    }
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    testImplementation("com.ninja-squad:springmockk:4.0.0"){
        exclude(group = "junit" ,module ="junit")
    }
    testImplementation(kotlin("test"))
    //testImplementation(kotlin("it"))

}
repositories {
    mavenCentral()
}