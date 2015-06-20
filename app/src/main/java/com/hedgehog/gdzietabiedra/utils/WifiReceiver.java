package com.hedgehog.gdzietabiedra.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by Adam on 2015-06-18.
 */
public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Location lastLocation = ((LocationManager) Biedra.getAppContext().getSystemService(Context.LOCATION_SERVICE)).getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info != null) {
            if(info.isConnected()) {
                Log.d("wifi ", "connected");

                Database.populate(lastLocation.getLatitude(), lastLocation.getLongitude());
            }
        }
    }
}