//@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.dokka) apply false
    alias(libs.plugins.io.gitlab.arturbosch.detekt) apply false
    alias(libs.plugins.com.autonomousapps.dependency.analysis) apply false
}

buildscript {

    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = java.util.Properties().apply {
        load(localPropertiesFile.inputStream())
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
}
