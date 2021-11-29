buildscript {
    // ... Gradle Plugin and Kotlin
    val gradleVersion: String by extra { "7.0.3" }
    val kotlinVersion: String by extra { "1.6.0" }

    // ... Hilt (https://developer.android.com/jetpack/androidx/releases/hilt)
    val hiltVersion: String by extra { "2.38.1" }
    val hiltNavVersion: String by extra { "1.0.0-beta01" }

    // ... Core Libraries
    val coreKtxVersion: String by extra { "1.7.0" }
    val appCompatVersion: String by extra { "1.3.1" }

    // ... Activity and Fragment
    val activityKtxVersion: String by extra { "1.4.0" }
    val fragmentKtxVersion: String by extra { "1.4.0" }

    // ... Lifecycle
    val lifecycleVersion: String by extra { "2.4.0" }

    // ... Material Design
    val materialVersion: String by extra { "1.4.0" }

    // Compose (https://developer.android.com/jetpack/compose/setup)
    val composeVersion: String by extra { "1.0.5" }

    // Coroutine (https://developer.android.com/kotlin/coroutines)
    val coroutineVersion: String by extra { "1.3.9" }

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}