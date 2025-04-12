plugins {
    id("java")
    id("org.sonarqube") version "6.0.1.5171"
}

sonar {
    properties {
        property("sonar.projectKey", "nor-codes_yamlquest")
        property("sonar.organization", "nor-codes")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

group = "com.yamlquest"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}