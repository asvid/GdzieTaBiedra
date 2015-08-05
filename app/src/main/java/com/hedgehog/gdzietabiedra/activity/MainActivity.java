package com.hedgehog.gdzietabiedra.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.SupportMapFragment;
import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.fragments.MapFragment;
import com.hedgehog.gdzietabiedra.fragments.ShopListFragment;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.utils.Database;
import com.hedgehog.gdzietabiedra.utils.MessageEvent;
import com.rey.material.widget.TabPageIndicator;

import java.util.Locale;

import butterknife.ButterKnife;
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
        EventBus.getDefault().register(this);

        String shopId = getIntent().getStringExtra("shopId");
        //Log.d("TAG", shopId);
        if(shopId != null){
            Log.d("TAG", shopId);
            Shop mItem = Database.getById(shopId);
            Log.d("TAG", mItem.toString());
            EventBus.getDefault().post(new MessageEvent("pokaż sklep", mItem, MessageEvent.types.ITEM_CLICK));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("tag", "onRestart");
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
