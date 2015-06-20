package com.hedgehog.gdzietabiedra.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;

public class Notify extends Service implements LocationListener {

    private LocationManager mLocationManager;
    private Location lastLocation;

    public Notify() {
        // And From your main() method or any other method
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate(){
        super.onCreate();

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, Const.locationTime, Const.distance, this);

        //lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lastLocation = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        Log.d("pierwsza lokalizacja: ",lastLocation.toString());

        if(Database.getAll().size() == 0){
            Database.populate(lastLocation.getLatitude(), lastLocation.getLongitude());
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        Shop closest = Database.getListClosest().get(0);
                Log.d("najblizej: ", Double.parseDouble(closest.getDistance()) + "");
        if(Double.parseDouble(closest.getDistance()) < Const.distanceAlertRange){
            Log.d("location", lastLocation.toString());
            sendNotification(getString(R.string.shop_close), getString(R.string.shop_close_text) + closest.getName() + ", " +closest.getImportQuery() , 0);
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
    private void sendNotification(String title, String text, int mNotificationId){

        NotificationCompat.BigTextStyle inboxStyle =
                new NotificationCompat.BigTextStyle();

        inboxStyle.setBigContentTitle(title);
        inboxStyle.bigText(text);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setStyle(inboxStyle);

        mBuilder.setLights(Color.RED, 500, 500);

// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
