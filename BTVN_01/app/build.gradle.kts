// Áp dụng các plugin cần thiết cho ứng dụng Android và Kotlin.
plugins {
    alias(libs.plugins.android.application) // Plugin ứng dụng Android.
    alias(libs.plugins.kotlin.android)     // Plugin Kotlin cho Android.
    alias(libs.plugins.kotlin.compose)     // Plugin Jetpack Compose.
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" // Plugin Kotlin Serialization.
    id("kotlin-kapt")                      // Plugin Kotlin Annotation Processing Tool.
    id("org.jetbrains.kotlin.kapt")        // Đảm bảo kapt được cấu hình.
    alias(libs.plugins.hilt)               // Plugin Hilt cho dependency injection.
}

// Cấu hình dự án Android.
android {
    namespace = "com.example.btvn_01" // Không gian tên của ứng dụng.
    compileSdk = 35 // Phiên bản SDK để biên dịch.

    defaultConfig {
        applicationId = "com.example.btvn_01" // ID ứng dụng duy nhất.
        minSdk = 24 // Phiên bản SDK tối thiểu.
        targetSdk = 35 // Phiên bản SDK mục tiêu.
        versionCode = 1 // Mã phiên bản ứng dụng.
        versionName = "1.0" // Tên phiên bản ứng dụng.

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Runner kiểm thử.
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // Cấu hình các loại build.
    buildTypes {
        release {
            isMinifyEnabled = false // Không thu nhỏ mã trong bản phát hành.
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    // Tùy chọn biên dịch Java.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Phiên bản nguồn Java.
        targetCompatibility = JavaVersion.VERSION_11 // Phiên bản đích Java.
    }
    // Tùy chọn biên dịch Kotlin.
    kotlinOptions {
        jvmTarget = "11" // Phiên bản JVM target.
    }
    // Kích hoạt Jetpack Compose.
    buildFeatures {
        compose = true
    }
    // Tùy chọn Compose.
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Phiên bản trình biên dịch Kotlin cho Compose.
    }
    // Cấu hình đóng gói.
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/io.netty.versions.properties"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/ASL2.0"
        }
    }
}

// Khai báo các thư viện phụ thuộc.
dependencies {
    // Import Compose BOM để quản lý phiên bản Compose nhất quán.
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.navigation:navigation-compose:2.8.0-beta02") // Thư viện Navigation Compose.
    implementation("io.coil-kt:coil-compose:2.6.0") // Thư viện Coil cho tải ảnh.

    // Các thư viện AndroidX Core và Lifecycle.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0") // LiveData ktx.

    // Các thư viện Jetpack Compose UI và Material Design.
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview) // Công cụ xem trước.
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended:1.6.7") // Biểu tượng Material Design mở rộng.

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1") // ViewModel cho Compose.

    // Retrofit để gọi API.
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Converter Gson cho Retrofit.

    // Coroutines.
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // OkHttp cho debug.
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Hilt cho Dependency Injection.
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    // Các thư viện kiểm thử.
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
// Cấu hình KAPT.
kapt {
    correctErrorTypes = true
}