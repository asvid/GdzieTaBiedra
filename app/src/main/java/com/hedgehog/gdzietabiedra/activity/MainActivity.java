package com.hedgehog.gdzietabiedra.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.SupportMapFragment;
import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.fragments.MapFragment;
import com.hedgehog.gdzietabiedra.fragments.ShopListFragment;
import com.hedgehog.gdzietabiedra.utils.Database;
import com.rey.material.widget.TabPageIndicator;

import java.util.Locale;

import butterknife.ButterKnife;

public class MainActivity extends ActionBarActivity {

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
//        Log.d("biedra", Database.getClosest().size() + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
            // Show 3 total pages.
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
