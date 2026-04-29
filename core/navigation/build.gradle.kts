plugins {
    id("skydoves.pokedex.android.library")
    id("skydoves.pokedex.android.library.compose")
    alias(libs.plugins.kotlinx.serialization)
    id("skydoves.pokedex.android.hilt")
    id("skydoves.pokedex.spotless")
}

android {
    namespace = "com.example.micromod.core.navigation"
}

dependencies {
    api(projects.core.designsystem)

    implementation(projects.core.model)

    implementation(libs.androidx.core)
    implementation(libs.kotlinx.coroutines.android)

    api(libs.androidx.navigation3.ui)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}