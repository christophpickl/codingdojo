import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    id("com.adarshr.test-logger") version "1.6.0"
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.testng:testng:6.14.3")
    testImplementation("com.natpryce:hamkrest:1.6.0.0")
}

repositories {
    jcenter()
}

tasks.withType(KotlinCompile::class.java).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType(Test::class.java).all {
    useTestNG()
}

task("testCsvViewer", Test::class) {
    useTestNG(closureOf<TestNGOptions> {
        includeGroups = setOf("csvviewer")
    })

}

task("testWordCount", Test::class) {
    useTestNG(closureOf<TestNGOptions> {
        includeGroups = setOf("wordcount")
    })
}
