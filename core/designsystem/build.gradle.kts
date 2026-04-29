plugins {
    id("skydoves.pokedex.android.library")
    id("skydoves.pokedex.android.library.compose")
    id("skydoves.pokedex.spotless")
}

android {
    namespace = "com.example.micromod.designsystem"
}

dependencies {
    // image loading
    api(libs.coil.compose)

    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.animation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.material3)

    // Icons
    api(libs.androidx.compose.material.icons.core)
    api(libs.androidx.compose.material.icons.extended)

    api(projects.core.model)
}
