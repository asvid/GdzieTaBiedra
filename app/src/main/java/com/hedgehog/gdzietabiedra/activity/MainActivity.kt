package com.hedgehog.gdzietabiedra.activity

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hedgehog.gdzietabiedra.Di
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.adapters.TabsAdapter
import com.hedgehog.gdzietabiedra.fragments.MapFragment
import com.hedgehog.gdzietabiedra.utils.Database
import com.hedgehog.gdzietabiedra.utils.EventBusClasses
import com.rey.material.widget.TabPageIndicator
import de.greenrobot.event.EventBus
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    internal var tabs: TabPageIndicator by Delegates.notNull()
    internal var mPager: ViewPager by Delegates.notNull()
    internal var mPagerAdapter: TabsAdapter by Delegates.notNull()


    fun onEvent(event: EventBusClasses.ShopSelected) {
        mPager.currentItem = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTabs()
        handleNotificationIntent(intent)
    }

    private fun handleNotificationIntent(intent: Intent) {
        val shopId = intent.getStringExtra("shopId")
        if (shopId != null) {
            mPager.currentItem = 1
            (mPagerAdapter.getItem(1) as MapFragment).setCurrentShop(shopId)
        }
    }

    private fun setTabs() {
        tabs = findViewById(R.id.tabPage) as TabPageIndicator
        mPager = findViewById(R.id.pager) as ViewPager
        val titles = listOf<String>(getString(R.string.list), getString(R.string.map))
        mPagerAdapter = TabsAdapter(supportFragmentManager, titles)
        mPager.adapter = mPagerAdapter
        tabs.setViewPager(mPager)
    }

    public override fun onResume() {
        super.onResume()
        checkGPS()
        EventBus.getDefault().register(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null)
            handleNotificationIntent(intent)
    }

    public override fun onPause() {
        super.onPause()
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
}
