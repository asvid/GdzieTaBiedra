plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
}

apply(from = "../DokkaConfig.gradle")

android {
    defaultConfig {
        applicationId = Build.appId
        manifestPlaceholders["appName"] = "@string/app_name"
        versionCode = Build.versionCode
        versionName = Build.versionName
        buildToolsVersion = Build.buildToolsVersion
        minSdkVersion(Build.minSdkVersion)
        targetSdkVersion(Build.targetSdkVersion)
        compileSdkVersion(Build.compileSdkVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isZipAlignEnabled = true
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
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
        getByName("debug") {
            manifestPlaceholders["appName"] = "Biedra - Debug"
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
            isDebuggable = true
            multiDexEnabled = true
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
    compileOptions {
        isCoreLibraryDesugaringEnabled = true // adds support for LocalTime for API<26
        sourceCompatibility = JavaVersion.VERSION_1_8 // MapUtils won't work on API23 without it
        targetCompatibility = JavaVersion.VERSION_1_8 // MapUtils won't work on API23 without it
    }
    lintOptions {
        isAbortOnError = false
    }
    dexOptions {
        preDexLibraries = true
    }
    testOptions {
        animationsDisabled = true
        unitTests {
            isReturnDefaultValues = true
        }
    }
    packagingOptions {
        exclude("META-INF/proguard/androidx-annotations.pro")
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.kotlin)
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
    implementation(Android.swipeRefreshLayout)
    implementation(Android.preferences)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    coreLibraryDesugaring(Android.coreLibDesugaring)

    implementation(Libs.calendarView)

    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.ktx)

    implementation(Google.maps)
    implementation(Google.mapUtils)
    implementation(Google.mapUtilsKtx)
    implementation(Google.location)

    implementation(Ktor.clientAndroid)
    implementation(Ktor.clientOkHttp)
    implementation(Ktor.ktorSerialization)

    implementation(Libs.dexter)
    implementation(Libs.timber)

    implementation(platform(Firebase.bom))
    implementation(Firebase.crashlytics)
    implementation(Firebase.analytics)

    implementation(Compose.runtime)
    implementation(Compose.ui)
    implementation(Compose.uiTooling)
    implementation(Compose.runtimeLivedata)
    implementation(Compose.foundation)
    implementation(Compose.foundationLayout)
    implementation(Compose.material)
    implementation(Compose.materialIcons)
    implementation(Compose.animation)

    testImplementation(TestDeps.junit)
    testImplementation(TestDeps.junitParams)
    androidTestImplementation(TestDeps.runner)
    androidTestImplementation(TestDeps.espresso)
    testImplementation(Koin.test)
    testImplementation(Room.testing)
}

tasks.withType<Test>() {
    useJUnitPlatform()
}