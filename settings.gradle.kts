rootProject.name = "fitness-app-kmp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include(":composeApp")
include(":ui")
include(":shared")
include(":auth")
include(":splash")

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