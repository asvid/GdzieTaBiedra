import Koin.test
import org.gradle.internal.impldep.org.fusesource.jansi.AnsiRenderer.test

plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.jodaTime)

    //  Testing
//    testImplementation(TestDeps.junit)
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