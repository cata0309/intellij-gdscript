plugins {
    java
    antlr
    kotlin("jvm") version "1.3.50"
    id("org.jetbrains.intellij").version("0.4.9")
}

dependencies {
    antlr("org.antlr:antlr4:4.7.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.9.9")
    implementation("org.antlr:antlr4-intellij-adaptor:0.1")
}

repositories {
    jcenter()
    mavenCentral()
}

intellij {
    version = "2019.2"
}

registerGenerateCompletionDataTask()
tasks {
    compileKotlin {
        dependsOn(generateGrammarSource)
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    generateGrammarSource {
        arguments = listOf(
            "-package", "gdscript",
            "-no-listener",
            "-no-visitor"
        )
    }
}
