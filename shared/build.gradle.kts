plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)

            //http client
            implementation(libs.ktor.client.okhttp)

            api(libs.koin.android)
        }
        commonMain.dependencies {
            //compose ui
            api(compose.material3)
            api(compose.runtime)
            api(compose.ui)
            api(project(":ui"))

            //logger
            api(libs.kotling.logging)

            //koin dependency injection
            api(libs.koin.core)
            api(libs.koin.compose)

            //voyager navigation
            api(libs.voyager.navigator)
            api(libs.voyager.screenmodel)
            api(libs.voyager.koin)

            //http client
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.logging)

            //serialization
            api(libs.kotlinx.serialization.json)
        }
        iosMain.dependencies {
            //http client
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.gmail.bogumilmecel2.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}