plugins {
    id("skydoves.pokedex.android.library")
    id("skydoves.pokedex.android.hilt")
    id("skydoves.pokedex.spotless")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.micromod.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.model)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Retrofit
    implementation(libs.okhttp)
}
