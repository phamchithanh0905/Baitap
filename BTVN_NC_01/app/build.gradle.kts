plugins {
    // Reference plugins from libs.versions.toml
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Removed: alias(libs.plugins.kotlin.compose)
    // The 'org.jetbrains.kotlin.plugin.compose' plugin isn't needed here directly.
    // Compose Compiler Extension is handled via kotlinCompilerExtensionVersion in the android block.
}

android {
    namespace = "com.example.btvn_nc_01"
    compileSdk = 34 // Compile against Android API 34

    defaultConfig {
        applicationId = "com.example.btvn_nc_01"
        minSdk = 24 // Minimum API level
        targetSdk = 34 // Target API level (ensure >= 33 for Google Play)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Disable code minification for release
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8 // Java 8 compatibility
        targetCompatibility = JavaVersion.VERSION_1_8 // Java 8 compatibility
    }
    kotlinOptions {
        jvmTarget = "1.8" // Kotlin JVM target version
    }
    buildFeatures {
        compose = true // Enable Jetpack Compose
    }
    composeOptions {
        // Ensure this version is compatible with the Kotlin version in libs.versions.toml (Kotlin 1.9.22 -> Compose Compiler 1.5.10)
        kotlinCompilerExtensionVersion = "1.5.10" // Or 1.6.10 if using Kotlin 2.0.0
    }
    packagingOptions {
        resources {
            // Exclude problematic files from the APK
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core KTX and Lifecycle Runtime KTX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Always use Compose BOM to manage Compose library versions
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Compose Material3
    implementation(libs.androidx.material3)

    // Accompanist Permissions (from libs.versions.toml)
    implementation(libs.accompanist.permissions)

    // Material Icons Extended (from libs.versions.toml)
    implementation(libs.material.icons.extended)

    // Testing dependencies
    testImplementation(libs.junit) // JUnit for unit tests
    androidTestImplementation(libs.androidx.junit) // AndroidX JUnit for instrumented tests
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI tests
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose BOM for test dependencies
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose UI testing
    debugImplementation(libs.androidx.ui.tooling) // Compose tooling for previews
    debugImplementation(libs.androidx.ui.test.manifest) // Compose test manifest
}