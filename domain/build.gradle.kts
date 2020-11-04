plugins {
    id("com.android.library")
}

android {
    compileSdkVersion(Build.compileSdkVersion)
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.jodaTime)

    //  Testing
    testImplementation(TestDeps.junit)
    testImplementation(Libs.jodaTime)
}