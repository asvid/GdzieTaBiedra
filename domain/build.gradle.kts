plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.jodaTime)

    //  Testing
//    testImplementation(TestDeps.junit)
    testImplementation(Libs.jodaTime)
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.junitParams)
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}