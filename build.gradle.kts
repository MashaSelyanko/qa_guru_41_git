plugins {
    id("java")
}

group = "selyanko.masha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.17.0")
    testImplementation("org.aeonbits.owner:owner:1.0.12")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    systemProperties(System.getProperties() as Map<String, String>)
}