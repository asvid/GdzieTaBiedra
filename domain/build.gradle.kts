plugins {
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.jodaTime)

    //  Testing
    testImplementation(Libs.jodaTimeTest)
    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.junitParams)
    testImplementation("com.tngtech.archunit:archunit-junit5:0.14.1")
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test>(){
    useJUnitPlatform()
}