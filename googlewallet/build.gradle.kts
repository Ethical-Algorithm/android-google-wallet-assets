@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
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