import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    application
}

group = "lab.kotlin.fundamentals"
version = "1.0.0"

repositories {
    mavenCentral()
}

var ktorVersion = "1.6.3"

dependencies {
    // KTOR for JVM
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    // CIO is a fully asynchronous coroutine-based engine that can be used
    // for both JVM and Android platforms. It supports only HTTP/1.x for now.
    implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")
    // The Apache engine supports HTTP/1.1 and provides multiple configuration options
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    //The Jetty engine supports only HTTP/2
    implementation("io.ktor:ktor-client-jetty:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.2")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
