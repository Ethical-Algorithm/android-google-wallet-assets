@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "net.ealgorithm.googlewallet"
    compileSdk = rootProject.extra["compileSDKVersion"].toString().toInt()

    defaultConfig {
        minSdk = rootProject.extra["minSDKVersion"].toString().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                          "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = rootProject.extra["javaVersionValue"] as JavaVersion
        targetCompatibility = rootProject.extra["javaVersionValue"] as JavaVersion
    }
    kotlinOptions {
        jvmTarget = rootProject.extra["javaVersionValue"].toString()
    }
}

dependencies {
    implementation(libs.androidx.core)

    // Testing
    testImplementation(libs.junit.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

// maven
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.ealgorithm"
            artifactId = "google-wallet-assets"
            version = "${rootProject.extra["moduleVersionName"]}.${rootProject.extra["moduleVersionCode"]}"
            artifact(File("build/outputs/aar/googlewallet-release.aar"))

            // Optionally, you can specify additional publication settings
            pom {
                // Customize POM settings here
                name.set("Google Wallet Assets")
                description.set("This repository contains official Google Wallet assets to help integrate \"Add to Google Wallet\" functionality.")
                url.set("https://github.com/Ethical-Algorithm/android-google-wallet-assets")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/Ethical-Algorithm/android-google-wallet-assets?tab=MIT-1-ov-file#readme")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("jobernas")
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
            setUrl("https://maven.pkg.github.com/Ethical-Algorithm/android-google-wallet-assets")
            credentials {
                username = rootProject.extra["gitHubUser"].toString()
                password = rootProject.extra["gitHubToken"].toString()
            }
        }
    }
}