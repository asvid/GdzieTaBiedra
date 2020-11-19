plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)

    //  Testing
    testImplementation(TestDeps.junit)
}