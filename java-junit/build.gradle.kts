plugins {
    id("java")
}

group = "nl.vanwollingen.alerticorn"
version = "unspecified"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-core:0.1")
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-slack-notifier:0.1")
    testImplementation("nl.vanwollingen.alerticorn:alerticorn-junit:0.1")
}

tasks.test {
    useJUnitPlatform()
}