package com.hedgehog.gdzietabiedra.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.fragments.MapFragment;
import com.hedgehog.gdzietabiedra.fragments.ShopListFragment;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.utils.Biedra;
import com.hedgehog.gdzietabiedra.utils.Database;
import com.hedgehog.gdzietabiedra.utils.MessageEvent;
import com.rey.material.widget.TabPageIndicator;

import java.util.Locale;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    TabPageIndicator tabs;
    ViewPager mPager;
    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs = (TabPageIndicator) findViewById(R.id.tabPage);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        tabs.setViewPager(mPager);

        checkGPS();

        String shopId = getIntent().getStringExtra("shopId");
        //Log.d("TAG", shopId);
        if(shopId != null){
            Shop mItem = Database.getById(shopId);
            EventBus.getDefault().post(new MessageEvent("pokaż sklep", mItem, MessageEvent.types.ITEM_CLICK));
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    private void checkGPS() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE );
        if(!manager.isProviderEnabled( LocationManager.GPS_PROVIDER )){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.gps_title);
            alert.setMessage(R.string.gps_msg);
            alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();}
            });
            alert.setPositiveButton(R.string.go_to_settings, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Intent I = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(I);
                }
            });
            AlertDialog al_gps = alert.create();
            al_gps.show();
        }else{
            Location lastLocation = ((LocationManager) getSystemService(Context.LOCATION_SERVICE)).getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            ConnectivityManager connManager = (ConnectivityManager) Biedra.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(mWifi != null) {
                if(mWifi.isConnected() && lastLocation != null) {
                    Database.populate(lastLocation.getLatitude(), lastLocation.getLongitude());
                }
            }
        }
    }

    public void onEvent(MessageEvent event){
        if(event.type == MessageEvent.types.ITEM_CLICK)
            mPager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settings = new Intent(this, Settings.class);
            settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(settings);
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new ShopListFragment();
                    break;
                case 1:
                    fragment = new MapFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.list).toUpperCase(l);
                case 1:
                    return getString(R.string.map).toUpperCase(l);
            }
            return null;
        }
    }
}
