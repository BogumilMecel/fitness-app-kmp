rootProject.name = "fitness-app-compose-mutliplatform"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


include(":composeApp")
include(":ui")
include(":shared")
include(":auth")

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