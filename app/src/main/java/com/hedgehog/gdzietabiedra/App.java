package com.hedgehog.gdzietabiedra;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hedgehog.gdzietabiedra.utils.Api;
import com.hedgehog.gdzietabiedra.utils.Const;
import com.hedgehog.gdzietabiedra.utils.Notify;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Adam on 2015-06-15.
 */
public class App extends Application {

    private static Context context;
    private RestAdapter restAdapter;
    public static Api.Shop shopApi;
    private static Realm realm;


    public void onCreate() {
        super.onCreate();
        if (App.readBoolean(this, "radar", Const.radiusCheckDefault)) {
            startService(new Intent(this, Notify.class));
        }

        context = getApplicationContext();
        Realm.init(this);
        realm = Realm.getInstance(
                new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded()
                        .build());
        CookieManager cookieManager = new CookieManager();

        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCookieHandler(cookieManager);

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
                }).create();

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(Const.SERVER_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient)).build();

        shopApi = restAdapter.create(Api.Shop.class);
    }

    public static Realm getRealm() {
        return realm;
    }

    public static Context getAppContext() {
        return context;
    }

    public static void saveToPreferences(Context context, String preferenceName,
                                         String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context,
                                             String preferenceName,
                                             String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static void saveBoolean(Context context, String preferenceName,
                                   Boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }

    public static Boolean readBoolean(Context context, String preferenceName,
                                      Boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
}
