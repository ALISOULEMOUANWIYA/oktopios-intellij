import org.jetbrains.grammarkit.tasks.GenerateParserTask

plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.intellij.platform") version "2.3.0"
    id("org.jetbrains.grammarkit") version "2022.3.2"
}

group = "com.oktopios"
version = "1.0.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2023.3")
        pluginVerifier()
        zipSigner()
    }
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java", "src/main/kotlin", "src/main/gen")
        }
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("src/main/gen")
        }
    }
}

// GÃ©nÃ©ration du Lexer
tasks.withType<org.jetbrains.grammarkit.tasks.GenerateLexerTask> {
    sourceFile.set(file("src/main/grammars/Oktopios.flex"))
    targetDir.set("src/main/gen/com/oktopios/intellij/lexer")
    targetClass.set("OktopiosFlexLexer")
    purgeOldFiles.set(true)
}

// GÃ©nÃ©ration du Parser
tasks.withType<GenerateParserTask> {
    sourceFile.set(file("src/main/resources/com/oktopios/lang/Oktopios.bnf"))
    // Utilisation d'un chemin fixe dans src/main/gen pour Ã©viter les erreurs d'importation
    targetRoot.set(file("src/main/gen").absolutePath)
    pathToParser.set("com/oktopios/lang/parser/OktopiosParser.java")
    pathToPsiRoot.set("com/oktopios/lang/psi")
    purgeOldFiles.set(true)
}

// On s'assure que la gÃ©nÃ©ration prÃ©cÃ¨de la compilation
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("generateLexer", "generateParser")
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild.set("233")
            untilBuild.set("252.*")
        }
    }
    // DÃ©sactive cette tÃ¢che lente qui causait les erreurs "Unknown element" dans vos logs
    buildSearchableOptions.set(false)
}
