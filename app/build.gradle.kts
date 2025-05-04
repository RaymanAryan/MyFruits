plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.rayman.myfruits"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rayman.myfruits"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

dependencies {
    // Hilt for Dependency Injection
    // Hilt simplifies dependency injection in Android applications.
    // It helps manage dependencies across different components efficiently.
    implementation("com.google.dagger:hilt-android:2.55")

    // Hilt Compiler
    // The compiler generates necessary code for Hilt annotations.
    // Since you're using Kotlin, you need Kotlin Symbol Processing (KSP).
    ksp("com.google.dagger:hilt-compiler:2.55")

    // Hilt + Navigation for Jetpack Compose
    // This library allows seamless integration of Hilt with Jetpack Compose's Navigation Component.
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.12.1")
    //Retrofit(Main)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit Converter for Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}
