package com.hedgehog.gdzietabiedra.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop
import com.hedgehog.gdzietabiedra.utils.EventBusClasses
import de.greenrobot.event.EventBus
import kotlin.properties.Delegates

/**
 * Created by Adam on 2015-06-18.
 */
class ShopView(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var name: TextView = itemView.findViewById(R.id.shopName) as TextView
    internal var shopAddress: TextView = itemView.findViewById(R.id.shopAddress) as TextView
    internal var shopDistance: TextView = itemView.findViewById(R.id.shopDistance) as TextView
    internal var mItem: Shop by Delegates.notNull()

    init {
        itemView.setOnClickListener(this)
    }

    fun bindItem(item: Shop) {
        mItem = item
        name.text = item.name
        shopAddress.text = item.street + " " + item.streetNumber
        shopDistance.text = Math.round(java.lang.Double.parseDouble(item.distance)).toString() + " m"
    }

    override fun onClick(v: View) {
        EventBus.getDefault().post(EventBusClasses.ShopSelected(mItem))
    }
}
