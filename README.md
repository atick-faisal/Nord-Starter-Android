<h1 align="center">Nord Starter Android</h1>
<h5 align="center">🤖 Android Material Design App Template with Clean Architecture (MVVM) Written in Pure Kotlin ✅</h5>

<p align="center"><img alt="Kotlin 100%" src="https://img.shields.io/github/languages/top/atick-faisal/Nord-Starter-Android"/> <img alt="License: MIT" src="https://img.shields.io/github/license/atick-faisal/Nord-Starter-Android"/> <img alt="Issues" src="https://img.shields.io/github/issues/atick-faisal/Nord-Starter-Android"/> <img alt="Total lines" src="https://img.shields.io/tokei/lines/github/atick-faisal/Nord-Starter-Android"/></p>


<p align="center"><img width="200" height="200" src="https://4.bp.blogspot.com/-NnAkV5vpYuw/XNMYF4RtLvI/AAAAAAAAI70/kdgLm3cnTO4FB4rUC0v9smscN3zHJPlLgCLcBGAs/s1600/Jetpack_logo%2B%25282%2529.png"/></p>
<p align="center">Built with Android Jetpack Libraries</p>

<h5 align="center">Dependencies Used</h5>


``` gradle
dependencies {

    def ktx_version = "1.6.0"
    def app_compat_version = "1.3.0"
    def legacy_support_version = "1.0.0"

    def fragment_version = "1.3.5"
    def activity_version = "1.2.3"
    def lifecycle_version = "2.4.0-alpha02"

    def material_version = "1.4.0"
    def constraint_layout_version = "2.0.4"
    def glide_version = "4.12.0"

    def coroutine_version = "1.4.2"
    def room_version = "2.3.0"
    def data_store_version = "1.0.0-rc01"
    def work_version = "2.5.0"
    def hilt_work_version = "1.0.0"
    def serializer_version = "1.2.1"
    def retrofit_version = "2.9.0"
    def okhttp_version = "4.9.0"
    def timber_version = "4.7.1"
    def permissions_version = "3.0.0"
    def firebase_version = "28.2.1"
    def preferences_version = "1.1.1"

    implementation "androidx.core:core-ktx:$ktx_version"
    implementation "androidx.appcompat:appcompat:$app_compat_version"
    implementation "androidx.legacy:legacy-support-v4:$legacy_support_version"

    // ... UI
    implementation "com.google.android.material:material:$material_version"
    implementation "androidx.constraintlayout:constraintlayout:$constraint_layout_version"

    // ... Activity
    implementation "androidx.activity:activity-ktx:$activity_version"

    // ... Fragment
    implementation "androidx.fragment:fragment-ktx:$fragment_version"

    // ... Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // ... Lifecycle
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"

    // ... Coroutine
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version"

    // ... Room
    implementation "androidx.room:room-runtime:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

    // ... DataStore
    implementation "androidx.datastore:datastore-preferences:$data_store_version"

    // ... Work Manager
    implementation "androidx.work:work-runtime-ktx:$work_version"

    // ... Hilt
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:$hilt_version"

    // ... Hilt Work
    implementation "androidx.hilt:hilt-work:$hilt_work_version"
    kapt "androidx.hilt:hilt-compiler:$hilt_work_version"

    // ... Kotlin Serializer
    implementation "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializer_version"

    // ... Retrofit and OkHttp
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp_version"

    // ... Glide
    implementation "com.github.bumptech.glide:glide:$glide_version"
    kapt "com.github.bumptech.glide:compiler:$glide_version"

    // ... Timber
    implementation "com.jakewharton.timber:timber:$timber_version"

    // ... Easy Permissions
    implementation "pub.devrel:easypermissions:$permissions_version"

    // ... Firebase
    implementation platform("com.google.firebase:firebase-bom:$firebase_version")
    implementation 'com.google.firebase:firebase-messaging'
    implementation 'com.google.firebase:firebase-analytics'

    // ... Preferences
    implementation "androidx.preference:preference-ktx:$preferences_version"

    // ... Test
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"
    testImplementation "androidx.room:room-testing:$room_version"
}
```
