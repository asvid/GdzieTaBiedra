plugins {
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
}

apply(from = "../DokkaConfig.gradle")

android {
    defaultConfig {
        applicationId = Build.appId
        manifestPlaceholders["appName"] = "@string/app_name"
    }
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isZipAlignEnabled = true
            isMinifyEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile(file("proguard-rules.pro"))
            rootProject.file("proguard")
                    .listFiles()?.let { files ->
                        proguardFiles(*files
                                .filter { it.name.startsWith("proguard") }
                                .toTypedArray()
                        )
                    }
            manifestPlaceholders["appName"] = "@string/app_name"
        }
        getByName("debug") {
            manifestPlaceholders["appName"] = "Biedra - Debug"
            debuggable(true)
            multiDexEnabled = true
            minifyEnabled(false)
            firebaseCrashlytics {
                mappingFileUploadEnabled = false
            }
            splits.abi.isEnable = false
            splits.density.isEnable = false
            aaptOptions.cruncherEnabled = false
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    flavorDimensions("type")
    productFlavors {
        create("dev") {
            dimension = "type"
//            versionNameSuffix = "-dev"
//            applicationIdSuffix = ".dev"
        }
        create("production") {
            dimension = "type"
        }
    }

    variantFilter {
        if (buildType.name == "debug" && flavors[0].name != "dev") {
            ignore = true
        }
        if (buildType.name == "release" && flavors[0].name != "production") {
            ignore = true
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.coroutines)

    implementation(Koin.android)
    implementation(Koin.scope)
    implementation(Koin.viewmodel)
    implementation(Koin.fragment)

    implementation(Android.cardView)
    implementation(Android.constraintLayout)
    implementation(Android.recyclerView)
    implementation(Android.appCompat)
    implementation(Android.material)
    implementation(Android.navigationFragment)
    implementation(Android.navigationFragmentKtx)
    implementation(Android.navigationUi)
    implementation(Android.navigationUiKtx)
    implementation(Android.livedataKtx)
    implementation(Android.viewmodelKtx)

    implementation(Libs.calendarView)

    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.ktx)

    implementation(Google.maps)
    implementation(Google.location)

    implementation(Ktor.clientAndroid)
    implementation(Ktor.clientOkHttp)
    implementation(Ktor.ktorSerialization)

    implementation(Libs.jodaTime)
    implementation(Libs.dexter)
    implementation(Libs.timber)

    implementation(platform(Firebase.bom))
    implementation(Firebase.crashlytics)
    implementation(Firebase.analytics)
}

dependencies {
    testImplementation(TestDeps.junit)
    androidTestImplementation(TestDeps.runner)
    androidTestImplementation(TestDeps.espresso)
    testImplementation(Koin.test)
    testImplementation(Room.testing)
}