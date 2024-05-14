@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.dokka) apply false
    alias(libs.plugins.io.gitlab.arturbosch.detekt) apply false
    alias(libs.plugins.com.autonomousapps.dependency.analysis) apply false
    alias(libs.plugins.gradle.conventional.changelog) apply true
    id("maven-publish") apply true
}

buildscript {

    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = java.util.Properties().apply {
        load(localPropertiesFile.inputStream())
    }

    val keystorePropertiesFile = rootProject.file("keystore/keystore.properties")
    val keystoreProperties = java.util.Properties().apply {
        load(keystorePropertiesFile.inputStream())
    }

    extra["compileSDKVersion"] = 34
    extra["targetSDKVersion"] = 34
    extra["minSDKVersion"] = 24
    // Compile Options
    extra["javaVersionValue"] = JavaVersion.VERSION_11
    // Runner
    extra["testInstrumentationRunnerValue"] = "androidx.test.runner.AndroidJUnitRunner"

    // Modules Config
    val versionName: String = project.findProperty("versionName").toString()
    val versionBuild: Int = (project.findProperty("versionBuild").toString()).toIntOrNull() ?: 1

    extra["moduleVersionName"] = versionName
    extra["moduleVersionCode"] = versionBuild

    // GitHub Tokens
    extra["gitHubUser"] = localProperties["gitHubUser"].toString()
    extra["gitHubToken"] = localProperties["gitHubToken"].toString()

    // Keystore Properties
    extra["storeFile"] = keystoreProperties["storeFile"].toString()
    extra["storePassword"] = keystoreProperties["storePassword"].toString()
    extra["keyAlias"] = keystoreProperties["keyAlias"].toString()
    extra["keyPassword"] = keystoreProperties["keyPassword"].toString()
}

// gradle-conventional-changelog Plugin
changelog {
    // the name of the file where to write the changelog.
    file = "docs/Changelog.md" // Defaults to "CHANGELOG.md"
    // the name of the app/project for which you are generating a changelog.
    appName = "... Module" // Defaults to an empty string.
    // version number for this changelog
    versionNum =
        "${extra["moduleVersionName"]}.${extra["moduleVersionCode"]}" // Guesses next version using commits content and defaults to an empty string.
    // version name
    // versionText project.coreName //Defaults to an empty string.
    // URL of the repository where the commits can be found. Plugin will append /commits at the end.
    // repoUrl 'https://dev.azure.com/necamdpf/Mobile%20WAY/_git/core-android-module' //Defaults to an empty string.
    // URL of the bug tracker where closed issues can be found. Plugin will append /issues at the end.
    // trackerUrl 'https://dev.azure.com/necamdpf/Mobile%20WAY/_git/core-android-module' //Defaults to an empty string.
    // lower limit to filter the git log command ( tag or commit hash)
    // from '1.0.0' //Defaults to previous git tag found or first commit if none found.
    // upper limit filter the git log command.
    to = "HEAD" // Defaults to HEAD.
    // regex used to filter the conventional changelog commits for this changelog
    match = "^fix|^feat|^fix|^perf|^refactor|BREAKING" // Defaults to the value in the example.
}

// maven
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.ealgorithm"
            artifactId = "..."
            version = "${extra["moduleVersionName"]}.${extra["moduleVersionCode"]}"
            artifact(File(".../build/outputs/aar/...-release.aar"))

            // Optionally, you can specify additional publication settings
            pom {
                // Customize POM settings here
                name.set("Your Library Name")
                description.set("Your Library Description")
                url.set("https://your-library-url.com")
                licenses {
                    license {
                        name.set("Ethical Distribution License")
                        url.set("https://your-license-url.com")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
//                        id.set("your-developer-id")
                        name.set("Bernardo Luis")
                        email.set("bernardo.luis@ealgorithm.net")
                    }
                }
            }
        }
    }

    // Ethical Modules Feed
    repositories {
        maven {
            name = "GitHubPackages"
            setUrl("https://maven.pkg.github.com/Ethical-Algorithm/android-packages")
            credentials {
                username = extra["gitHubUser"].toString()
                password = extra["gitHubToken"].toString()
            }
        }
    }
}
