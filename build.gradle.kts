import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile(group = "org.testng", name = "testng", version = "6.14.3")
    testCompile(group = "com.natpryce", name = "hamkrest", version = "1.6.0.0")
}

repositories {
    jcenter()
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }
}
