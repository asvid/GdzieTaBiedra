package com.hedgehog.gdzietabiedra.activity

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hedgehog.gdzietabiedra.Di
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.fragments.MapFragment
import com.hedgehog.gdzietabiedra.fragments.ShopListFragment
import com.hedgehog.gdzietabiedra.utils.Database
import com.hedgehog.gdzietabiedra.utils.MessageEvent
import com.rey.material.widget.TabPageIndicator
import de.greenrobot.event.EventBus
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    internal var tabs: TabPageIndicator by Delegates.notNull()
    internal var mPager: ViewPager by Delegates.notNull()
    internal var mPagerAdapter: PagerAdapter by Delegates.notNull()


    fun onEvent(event: MessageEvent) {
        if (event.type === MessageEvent.types.ITEM_CLICK)
            mPager.currentItem = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabs = findViewById(R.id.tabPage) as TabPageIndicator
        mPager = findViewById(R.id.pager) as ViewPager
        mPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mPager.adapter = mPagerAdapter
        tabs.setViewPager(mPager)

        checkGPS()

        val shopId = intent.getStringExtra("shopId")
        if (shopId != null) {
            val mItem = Database.getById(shopId)
            EventBus.getDefault().post(MessageEvent("pokaż sklep", mItem,
                                                    MessageEvent.types.ITEM_CLICK))
        }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun checkGPS() {
        val manager = getSystemService(
                Context.LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle(R.string.gps_title)
            alert.setMessage(R.string.gps_msg)
            alert.setNegativeButton(R.string.no
            ) { dialog, which -> dialog.dismiss() }
            alert.setPositiveButton(R.string.go_to_settings
            ) { dialog, which ->
                val I = Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(I)
            }
            val al_gps = alert.create()
            al_gps.show()
        } else {
            val lastLocation = Di.locationService
                    .getLastLocation()

            if (lastLocation != null) {
                Database.populate(lastLocation.latitude,
                                  lastLocation.longitude)

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val settings = Intent(this, Settings::class.java)
            settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(settings)
        }

        return super.onOptionsItemSelected(item)
    }

    private inner class SectionsPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(
            fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0    -> return ShopListFragment()
                1    -> return MapFragment()
                else -> {
                    return ShopListFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            val l = Locale.getDefault()
            when (position) {
                0 -> return getString(R.string.list).toUpperCase(l)
                1 -> return getString(R.string.map).toUpperCase(l)
            }
            return null
        }
    }

    override fun onResume() {
        super.onResume()
        checkGPS()
    }
}
