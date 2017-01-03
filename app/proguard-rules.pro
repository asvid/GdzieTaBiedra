# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in J:\development\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn javax.**
-dontwarn io.realm.**

-dontwarn com.facebook.android.BuildConfig
-dontwarn rx.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.*
-dontwarn retrofit.appengine.UrlFetchClient
-keepattributes *Annotation*
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepattributes Signature

-keepclassmembers class ** {
    public void onEvent(**);
}

-keep class com.hedgehog.gdzietabiedra.pojo.Shops.Shop
-keep class com.hedgehog.gdzietabiedra.pojo.Shops.ShopList
-keep class com.hedgehog.gdzietabiedra.utils.MessageEvent