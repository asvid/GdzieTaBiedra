# Crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

# Timber
-dontwarn org.jetbrains.annotations.**

#### OkHttp, Retrofit and Moshi
-dontwarn okhttp3.**
-dontwarn retrofit2.Platform$Java8
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class com.hedgehog.gdzietabiedra.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.hedgehog.gdzietabiedra.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class com.hedgehog.gdzietabiedra.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}
