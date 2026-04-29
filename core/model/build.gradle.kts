plugins {
    id("skydoves.pokedex.android.library")
    id("skydoves.pokedex.android.hilt")
    id("skydoves.pokedex.spotless")
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.micromod.core.model"
}

dependencies {

    // compose stable marker
    compileOnly(libs.compose.stable.marker)

    // Kotlin Serialization for Json
    implementation(libs.kotlinx.serialization.json)

}
