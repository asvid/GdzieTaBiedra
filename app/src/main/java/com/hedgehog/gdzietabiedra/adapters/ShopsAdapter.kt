package com.hedgehog.gdzietabiedra.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop
import com.hedgehog.gdzietabiedra.viewHolders.ShopView

/**
 * Created by Adam on 2015-06-18.
 */
class ShopsAdapter(private val mItems: List<Shop>) : RecyclerView.Adapter<ShopView>() {
    private var mPreviousPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopView {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.shop_item, parent, false)
        return ShopView(view)
    }

    override fun onBindViewHolder(holder: ShopView, position: Int) {
        val item = mItems[position]
        holder.bindItem(item)
        mPreviousPosition = position
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}
