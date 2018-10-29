object External {
  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
  const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
  const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
  const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

  const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rx}"
  const val rxbinding = "com.jakewharton.rxbinding3:rxbinding:3.0.0-alpha1"
  const val rxbindingCore = "com.jakewharton.rxbinding3:rxbinding-core:3.0.0-alpha1"
  const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"

  const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

  const val leakcanaryDebug = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
  const val leakcanaryRelease = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"

  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
  const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
  const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

  const val dexter = "com.karumi:dexter:5.0.0"

  const val firebaseCore = "com.google.firebase:firebase-core:16.0.4"
  const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.5"

}

object Realm {
  const val plugin = "io.realm:realm-gradle-plugin:${Versions.realm}"
  const val nameHelper = "dk.ilios:realmfieldnameshelper:1.1.1"
}

object Uber {
  const val ribCompilerTest = "com.uber.rib:rib-compiler-test:0.9.1"
  const val ribs = "com.uber.rib:rib-android:0.9.1"
  const val ribTestUtils = "com.uber.rib:rib-test-utils:0.9.1"
}

object Firebase {
  const val core = "com.google.firebase:firebase-core:11.2.0"
  const val crash = "com.google.firebase:firebase-crash:11.2.0"
  const val messaging = "com.google.firebase:firebase-messaging:11.2.0"
}

object Google {
  const val maps = "com.google.android.gms:play-services-maps:${Versions.googlePlayServices}"
  const val location = "com.google.android.gms:play-services-location:${Versions.googlePlayServices}"
}