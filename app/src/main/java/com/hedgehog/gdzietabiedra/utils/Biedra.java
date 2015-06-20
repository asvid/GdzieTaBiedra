package com.hedgehog.gdzietabiedra.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.Date;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Adam on 2015-06-15.
 */
public class Biedra extends Application {

    private static Context context;
    private static CookieStore cookieStore;
    private static OkHttpClient picassoClient;
    private static Picasso picasso;
    private RestAdapter restAdapter;
    public static Api.Shop shopApi;
    private static Realm realm;



    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, Notify.class));

        context = getApplicationContext();
        realm = Realm.getInstance(Biedra.getAppContext());
        CookieManager cookieManager = new CookieManager();
        cookieStore = cookieManager.getCookieStore();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCookieHandler(cookieManager);
        picassoClient = okHttpClient;

        OkHttpDownloader downloader = new OkHttpDownloader(okHttpClient);
        picasso = new Picasso.Builder(this).downloader(downloader).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.e("image error", "irl: " + uri + " / exception: " + exception);
            }
        }).build();
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Const.SERVER_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .build();

        shopApi = restAdapter.create(Api.Shop.class);
    }
    public static Realm getRealm() {
        return realm;
    }
    public static Context getAppContext() {
        return Biedra.context;
    }

    public static OkHttpClient getPicassoClient(){return picassoClient;}
    public static Picasso getPicasso(){return picasso;}

    public static CookieStore getCookieStore(){return cookieStore;}

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
}
