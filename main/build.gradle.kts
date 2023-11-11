plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.jetbrain.kotlin)
    alias(libs.plugins.android.jetbrain.kotlin.kapt)
    alias(libs.plugins.android.google.hilt)

}

android {
    namespace = "com.codigo.healthcare"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.codigo.healthcare"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":base"))
    implementation(project(":shared"))
    implementation(project(":vitamins"))
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.network)
    implementation(libs.bundles.navigation)
    implementation(libs.recyclerview)

    /* Hilt */
    implementation(libs.hiltLib)
    implementation(libs.hiltNavLib)
    kapt(libs.hiltCompiler)

//    appcompat", "constraintLayout", "material", "coreKtx", "fragmentKtx", "ssp", "sdp
    implementation(libs.bundles.support)
}