plugins {
    id("java")
    id("io.qameta.allure") version "4.0.2"
}

group = "selyanko.masha"
version = "1.0-SNAPSHOT"


val slf4jVersion = "2.0.17"
allure {
        version.set("2.19.0")
    adapter {                                   // отвечает за появление папки build/allure-results
        aspectjWeaver.set(true)                 //обработка аннотации @Step
        frameworks {
            junit5 {                            //фреймворк
                adapterVersion.set("2.19.0")    //версия интеграции фреймворка и Allure
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0")) //обращаем внимание на bom
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.15.0")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.codeborne:pdf-test:1.5.0")
    testImplementation("com.codeborne:xls-test:1.4.3")
    implementation("com.opencsv:opencsv:5.12.0")
    implementation("com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:2.21.3")
    implementation("io.qameta.allure:allure-selenide:2.34.0")

    }

tasks.test {
    useJUnitPlatform()
}