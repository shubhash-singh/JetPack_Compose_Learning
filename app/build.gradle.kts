plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
}

android {
    namespace = "com.ragnar.jetpack_compose_learning"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ragnar.jetpack_compose_learning"
        minSdk = 28
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

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.09.01"))
    // Core Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    // Activity Compose (matches stable BOM)
    implementation("androidx.activity:activity-compose:1.9.3")
    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.8.0")
    // Icons
    implementation("androidx.compose.material:material-icons-extended")
    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling")

}