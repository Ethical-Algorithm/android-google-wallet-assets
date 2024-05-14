import java.net.URI
import java.util.Properties

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    val localProperties = Properties()
    localProperties.load(File(rootDir.absolutePath + "/local.properties").inputStream())

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // If you're using Ethical Algorithm Packages
        maven {
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/Ethical-Algorithm/android-packages")
            credentials {
                username = localProperties["gitHubUser"].toString()
                password = localProperties["gitHubToken"].toString()
            }
        }
        // maven { url('https://plugins.gradle.org/m2/') }
    }
}
rootProject.name = "EAGoogleWalletAssets"
include(":googlewallet")
