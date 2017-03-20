package com.hedgehog.gdzietabiedra.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hedgehog.gdzietabiedra.fragments.MapFragment
import com.hedgehog.gdzietabiedra.fragments.ShopListFragment
import java.util.*

class TabsAdapter(fm: FragmentManager, val titles: List<String>) : FragmentPagerAdapter(fm) {

    private var shopsFragment: ShopListFragment = ShopListFragment()
    private var mapFragment: MapFragment = MapFragment()

    override fun getItem(position: Int): Fragment {
        when (position) {
            0    -> return shopsFragment
            1    -> return mapFragment
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
            0 -> return titles[0].toUpperCase(l)
            1 -> return titles[1].toUpperCase(l)
        }
        return null
    }
}