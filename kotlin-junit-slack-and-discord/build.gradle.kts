plugins {
    kotlin("jvm") version "2.1.0"
}

group = "nl.vanwollingen.alerticorn"
version = "unspecified"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-core:0.1")
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-slack-notifier:0.1")
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-discord-notifier:0.1")
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-junit:0.1")
    testImplementation(kotlin("test"))
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnitTest.kt Jupiter test framework
            useJUnitJupiter("5.10.3")
        }
    }
}

kotlin {
    jvmToolchain(21)
}