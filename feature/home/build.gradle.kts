plugins {
  id("skydoves.pokedex.android.feature")
  id("skydoves.pokedex.android.hilt")
}

android {
  namespace = "com.example.micromod.feature.home"
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:network"))

    implementation(project(":core:designsystem"))

}