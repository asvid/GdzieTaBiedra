plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(Build.compileSdkVersion)
}

dependencies {
    implementation(Libs.kotlin)

    implementation(Libs.timber)

    implementation(Libs.jodaTime)

    //  Testing
    testImplementation(TestDeps.junit)
    androidTestImplementation(TestDeps.runner)
    androidTestImplementation(TestDeps.espresso)
    testImplementation(Libs.jodaTime)
}