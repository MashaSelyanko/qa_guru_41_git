plugins {
    id("java")
}

group = "selyanko.masha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.15.0")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.codeborne:pdf-test:1.5.0")
    testImplementation("com.codeborne:xls-test:1.4.3")
    implementation("com.opencsv:opencsv:5.12.0")
    implementation("com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:2.21.3")
    }

tasks.test {
    useJUnitPlatform()
}