import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.qsqnk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val exposedVersion = "0.38.2"

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.petersamokhin.vksdk:core:0.0.8")
    implementation("com.petersamokhin.vksdk:http-client-jvm-okhttp:0.0.8")
    implementation("com.petersamokhin.vksdk:http-client-common-ktor:0.0.8")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.postgresql:postgresql:42.2.2")

}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}