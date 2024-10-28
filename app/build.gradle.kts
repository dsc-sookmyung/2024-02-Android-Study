import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
}

val properties =
    Properties().apply { load(project.rootProject.file("local.properties").inputStream()) }


android {
    namespace = "com.gdg.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gdg.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true

            buildConfigField("String", "BASE_URL", properties["base.url"].toString())
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Compose 버전
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx) // LiveData를 위한 라이브러리
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ViewModel과 Compose 통합
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    // LiveData KTX
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    // Navigation Compose 최신 버전으로 업데이트
    implementation("androidx.navigation:navigation-compose:2.7.0")


    // Compose 라이브러리
    implementation("androidx.compose.runtime:runtime-livedata:1.4.0") // 이 줄을 추가하세요.

    implementation("androidx.compose.material3:material3:1.2.0") // 예시로 적절한 최신 버전으로 변경


    // 코루틴 라이브러리 추가
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // 최신 버전으로 업데이트
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // 최신 버전으로 업데이트

    // Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Test Dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
