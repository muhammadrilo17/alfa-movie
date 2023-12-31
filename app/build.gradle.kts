plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.rilodev.alfamovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rilodev.alfamovies"
        minSdk = 21
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
            buildConfigField("String", "BASE_URL", "\"${properties["BASE_URL"]}\"")
            buildConfigField("String", "BASE_IMAGE_URL", "\"${properties["BASE_IMAGE_URL"]}\"")

            buildConfigField("String", "API_KEY", "\"${properties["API_KEY"]}\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"${properties["BASE_URL"]}\"")
            buildConfigField("String", "BASE_IMAGE_URL", "\"${properties["BASE_IMAGE_URL"]}\"")

            buildConfigField("String", "API_KEY", "\"${properties["API_KEY"]}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.espresso:espresso-idling-resource:3.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("io.kotlintest:kotlintest-runner-junit4:3.3.2")

    implementation("androidx.activity:activity-ktx:1.7.2")

//    implementation("androidx.paging:paging-common-ktx:3.2.1")
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.room:room-paging:2.6.0-alpha03")

    implementation("com.github.bumptech.glide:glide:4.16.0")

    kapt("org.xerial:sqlite-jdbc:3.41.2.2")
//    implementation("androidx.room:room-common:2.6.0-alpha03")
//    ksp("androidx.room:room-compiler:2.6.0-alpha03")
    implementation("androidx.room:room-common:2.6.0-alpha03")
    implementation("androidx.room:room-ktx:2.6.0-alpha03")
    implementation("androidx.room:room-runtime:2.6.0-alpha03")
    kapt("androidx.room:room-compiler:2.6.0-alpha03")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-inline:3.12.4")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
}