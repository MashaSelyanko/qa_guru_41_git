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
    }

tasks.test {
    useJUnitPlatform()
}