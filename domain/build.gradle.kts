plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.jodaTime)

    //  Testing
    testImplementation(TestDeps.junit)
    testImplementation(Libs.jodaTime)
}