import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.vectorize)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ui"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidsvg.aar)
        }
        commonMain.dependencies {
            implementation(compose.material3)
            api(libs.vectorize)

            api(compose.components.resources)
            api(compose.material)
            api(compose.materialIconsExtended)

            //voyager navigation
            api(libs.voyager.navigator)
            api(libs.androidx.viewmodel)
            api(libs.koin.compose.viewmodel)
            api(libs.voyager.bottomsheet)
        }
        iosMain.dependencies {
            implementation(libs.stately.common)
        }
    }
}

android {
    namespace = "com.gmail.bogumilmecel2.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.gmail.bogumilmecel2.ui.composeResources"
    generateResClass = auto
}