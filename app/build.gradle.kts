plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.androidlead.movietheater"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.androidlead.movietheater"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //AndroidX
    implementation(libs.bundles.androidX)
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.lifecycle.livedata.ktx)
    debugImplementation(libs.compose.tooling)
    implementation(libs.bundles.ui)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.coil)
    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.runtime)
    implementation(libs.coil.compose)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    //noinspection UseTomlInstead
    implementation(libs.androidx.room.ktx)
}