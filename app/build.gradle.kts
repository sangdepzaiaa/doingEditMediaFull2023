plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 33
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
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    /** Firebase BOM **/
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-config")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-messaging")

    /** AndroidX Activity & Lifecycle **/
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-process:2.6.1")

    /** RecyclerView **/
    implementation("androidx.recyclerview:recyclerview:1.3.0")

    /** Koin **/
    implementation("io.insert-koin:koin-android:3.4.0")

    /** Retrofit + OkHttp **/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /** Ktor Client **/
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-android:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

    /** Glide **/
    implementation("com.github.bumptech.glide:glide:4.15.1")
    ksp("com.github.bumptech.glide:compiler:4.15.1")
    implementation("jp.wasabeef:glide-transformations:4.3.0")

    /** Room (KSP) **/
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

    /** Coroutines **/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    /** Play Core Review **/
    implementation("com.google.android.play:review:2.0.0")
    implementation("com.google.android.play:review-ktx:2.0.0")

    /** Splash Screen **/
    implementation("androidx.core:core-splashscreen:1.0.0")

    /** WorkManager **/
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    androidTestImplementation("androidx.work:work-testing:2.8.1")

    /** Multidex **/
    implementation("androidx.multidex:multidex:2.0.1")

    /** UI & hiệu ứng **/
    implementation("com.airbnb.android:lottie:6.0.0")
    implementation("com.github.zhouzhuo810:ZzHorizontalProgressBar:1.1.1")
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation("com.github.ybq:Android-SpinKit:1.4.0")

    /** ML Kit Face Detection **/
    implementation("com.google.mlkit:face-detection:16.1.5")

    /** Gson **/
    implementation("com.google.code.gson:gson:2.9.1")

}