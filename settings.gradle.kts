rootProject.name = "fitness-app-kmp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":composeApp")
include(":ui")
include(":core")
include(":core-models")
include(":core-utils")
include(":diary-models")
include(":diary-data")
include(":auth")
include(":splash")
include(":summary")
include(":diary")
include(":training")
include(":account")
include(":introduction")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}