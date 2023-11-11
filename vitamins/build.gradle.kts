@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.android.jetbrain.kotlin)
    alias(libs.plugins.android.jetbrain.kotlin.kapt)
    alias(libs.plugins.android.google.hilt)
    alias(libs.plugins.android.navigation.safeargs)
    id("kotlin-parcelize")
}

android {
    namespace = "com.codigo.welcome"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.network)
    implementation(libs.bundles.navigation)
    implementation(libs.recyclerview)
    implementation(libs.flexBox)

    /* Hilt */
    implementation(libs.hiltLib)
    implementation(libs.hiltNavLib)
    kapt(libs.hiltCompiler)

//    appcompat", "constraintLayout", "material", "coreKtx", "fragmentKtx", "ssp", "sdp
    implementation(libs.bundles.support)
}
