object Versions {
  const val kotlin = "1.4.10"
  const val kotlinSerialization = "1.0.0."
  const val ktor: String = "1.4.1"
  const val koin = "2.2.0-beta-2"
  const val coroutines = "1.3.9"
  const val roomDB = "2.2.5"
  const val googlePlayServices = "16.0.0"
  const val jodaTime = "2.10.6"
  const val timber = "4.7.1"
  const val firebaseBom = "26.0.0"
}

object Libs {
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
  const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

  const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
  const val dexter = "com.karumi:dexter:6.2.1"

  const val jodaTime = "net.danlew:android.joda:${Versions.jodaTime}"
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
}

object Android {
  const val cardView = "androidx.cardview:cardview:1.0.0"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
  const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
  const val appCompat = "androidx.appcompat:appcompat:1.2.0"
  const val material = "com.google.android.material:material:1.2.1"
  const val navigationFragment = "androidx.navigation:navigation-fragment:2.3.1"
  const val navigationUi = "androidx.navigation:navigation-ui:2.3.1"
  const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
  const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
  const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.3.1"
  const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:2.3.1"
  const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
}

object Koin {
  const val android = "org.koin:koin-android:${Versions.koin}"
  const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
  const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
  const val fragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
  const val workmanager = "org.koin:koin-androidx-workmanager:${Versions.koin}"
  const val compose = "org.koin:koin-androidx-compose:${Versions.koin}"
  const val test = "org.koin:koin-test:${Versions.koin}"
}

object Ktor {
  const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
  const val clientOkHttp = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
  const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
  const val ktorSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
}

object TestDeps {
  const val junit = "junit:junit:4.12"
  const val mockito = "org.mockito:mockito-core:2.0.42-beta"
  const val assertj = "org.assertj:assertj-core:2.6.0"
  const val compileTesting = "com.google.testing.compile:compile-testing:0.11"
  const val runner = "com.android.support.test:runner:1.0.2"
  const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
}
