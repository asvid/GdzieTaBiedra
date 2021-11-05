object Versions {
    const val kotlin = "1.5.31"
    const val kotlinSerialization = "1.3.0."
    const val ktor: String = "1.4.1"
    const val koin = "2.2.0-beta-2"
    const val coroutines = "1.5.2"
    const val roomDB = "2.4.0-beta01"
    const val googlePlayServices = "17.0.0"
    const val timber = "4.7.1"
    const val firebaseBom = "26.0.0"
    const val junit = "5.7.0"
    const val compose = "1.0.5"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val dexter = "com.karumi:dexter:6.2.1"

    const val calendarView = "com.github.sundeepk:compact-calendar-view:3.0.0"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.roomDB}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomDB}"
    const val ktx = "androidx.room:room-ktx:${Versions.roomDB}"
    const val testing = "androidx.room:room-testing:${Versions.roomDB}"
}

object Firebase {
    const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
}

object Google {
    const val maps = "com.google.android.gms:play-services-maps:${Versions.googlePlayServices}"
    const val location = "com.google.android.gms:play-services-location:${Versions.googlePlayServices}"
    const val mapUtils = "com.google.maps.android:android-maps-utils:2.2.0"
    const val mapUtilsKtx = "com.google.maps.android:maps-utils-ktx:2.2.0"
}

object Android {
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
    const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    const val appCompat = "androidx.appcompat:appcompat:1.3.1"
    const val material = "com.google.android.material:material:1.2.1"
    const val navigationFragment = "androidx.navigation:navigation-fragment:2.3.1"
    const val navigationUi = "androidx.navigation:navigation-ui:2.3.1"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.3.1"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:2.3.1"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val preferences = "androidx.preference:preference-ktx:1.1.1"
    const val coreLibDesugaring = "com.android.tools:desugar_jdk_libs:1.1.1"
}

object Compose{
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val materialIcons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val animation = "androidx.compose.animation:animation:${Versions.compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
}

object Koin {
    const val android = "org.koin:koin-android:${Versions.koin}"
    const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val fragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
    const val test = "org.koin:koin-test:${Versions.koin}"
}

object Ktor {
    const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val clientOkHttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
}

object TestDeps {
    const val runner = "com.android.support.test:runner:1.0.2"
    const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
    const val junit = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
    const val junitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
}
