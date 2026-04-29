plugins {
    id("skydoves.pokedex.android.feature")
    id("skydoves.pokedex.android.hilt")
}

android {
    namespace = "com.example.micromod.feature.detail"
}

dependencies {
    implementation(project(":core:navigation"))

    implementation(project(":core:designsystem"))
}