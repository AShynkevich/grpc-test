import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation(project(":grpc-stubs"))

    runtimeOnly("io.grpc:grpc-netty:${rootProject.ext["grpcVersion"]}")
}