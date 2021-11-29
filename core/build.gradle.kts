plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31

        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
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
        mlModelBinding = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val hiltVersion: String by rootProject.extra
    val ktxVersion: String by rootProject.extra
    val appCompatVersion: String by rootProject.extra
    val activityKtxVersion: String by rootProject.extra
    val fragmentKtxVersion: String by rootProject.extra
    val materialVersion: String by rootProject.extra
    val composeVersion: String by rootProject.extra
    val hiltNavVersion: String by rootProject.extra
    val lifecycleVersion: String by rootProject.extra
    val coroutineVersion: String by rootProject.extra

    api("androidx.core:core-ktx:$ktxVersion")
    api("androidx.appcompat:appcompat:$appCompatVersion")

    // ... Material Design
    api("com.google.android.material:material:$materialVersion")

    // ... Jetpack Compose
    api("androidx.compose.ui:ui:$composeVersion")
    api("androidx.compose.ui:ui-tooling:$composeVersion")
    api("androidx.compose.foundation:foundation:$composeVersion")
    api("androidx.compose.material:material:$composeVersion")
    api("androidx.compose.material:material-icons-core:$composeVersion")
    api("androidx.compose.material:material-icons-extended:$composeVersion")
    api("androidx.compose.runtime:runtime-livedata:$composeVersion")

    // ... Activity
    api("androidx.activity:activity-ktx:$activityKtxVersion")

    // ... Fragment
    api("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

    // ... Navigation
    api("androidx.hilt:hilt-navigation-compose:${hiltNavVersion}")

    // ... Lifecycle
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    api("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

    // ... Coroutine
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    // ... Hilt
    api("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
}