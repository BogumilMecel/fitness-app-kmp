plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.moko.resources)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

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
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidsvg.aar)
        }
        commonMain.dependencies {
            api(compose.material)
            implementation(compose.material3)
            api(compose.materialIconsExtended)
            api(libs.moko.resources)
            api(libs.moko.resources.compose)
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

    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.gmail.bogumilmecel2.ui"
    multiplatformResourcesClassName = "SharedRes"
}