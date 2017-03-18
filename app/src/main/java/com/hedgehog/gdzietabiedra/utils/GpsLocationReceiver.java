package com.hedgehog.gdzietabiedra.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.hedgehog.gdzietabiedra.App;

/**
 * Created by Adam on 2015-08-06.
 */
public class GpsLocationReceiver extends BroadcastReceiver{
    private LocationManager locationManager;
    private NetworkInfo mWifi;
    private ConnectivityManager connManager;
    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            try {
                gotLocation(location);
            } catch (Exception e) {
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(LocationManager.PROVIDERS_CHANGED_ACTION)) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            connManager = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 5000, 5, locationListener);
                Location gotLoc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                gotLocation(gotLoc);
            }
        }
    }

    private void gotLocation(Location gotLoc) {
        if(mWifi != null) {
            if(mWifi.isConnected() && gotLoc != null) {
                Database.populate(gotLoc.getLatitude(), gotLoc.getLongitude());
            }
        }
    }
}
