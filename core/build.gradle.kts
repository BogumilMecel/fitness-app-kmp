import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "core"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)

            //http client
            implementation(libs.ktor.client.okhttp)

            api(libs.koin.android)
            api(libs.room.runtime.android)
        }
        commonMain.dependencies {
            //compose ui
            api(compose.material3)
            api(compose.runtime)
            api(compose.ui)
            api(project(":ui"))
            api(project(":core-models"))

            //logger
            api(libs.kotling.logging)

            //koin dependency injection
            api(libs.koin.core)
            api(libs.koin.compose)

            //http client
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.logging)

            //serialization
            api(libs.kotlinx.serialization.json)

            //key-value
            api(libs.settings)
            api(libs.settings.serialization)
            api(libs.settings.coroutines)

            api(libs.kotlinx.date)

            //room
            api(libs.room.runtime)
            api(libs.sqlite.bundled)

            api(libs.kotlinx.coroutines)
            implementation(compose.components.resources)
            implementation(compose.foundation)
        }
        iosMain.dependencies {
            //http client
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.gmail.bogumilmecel2.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    ksp(libs.room.compiler)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.gmail.bogumilmecel2.core.composeResources"
    generateResClass = auto
}