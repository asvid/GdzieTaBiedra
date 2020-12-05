package com.hedgehog.gdzietabiedra.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.github.asvid.biedra.domain.shops.Shop
import com.github.asvid.biedra.domain.shops.SundayShopping
import com.github.asvid.biedra.domain.shops.getForToday
import com.github.asvid.biedra.domain.shops.printName
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.generateDistanceText
import kotlinx.android.synthetic.main.shop_list_item.view.*
import java.util.*

class ShopListAdapter(
        val mapButtonClicked: (Shop) -> Unit,
        val infoBUttonClicked: (Shop) -> Unit
) : RecyclerView.Adapter<ShopListItemVH>() {

    private var items: List<Shop> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListItemVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_item,
                parent, false)
        return ShopListItemVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShopListItemVH, position: Int) {
        val item = items[position]
        holder.setViewHolder(item)
        holder.view.item_body.setOnClickListener {
            infoBUttonClicked(item)
        }
        holder.view.map_button.setOnClickListener {
            mapButtonClicked(item)
        }
        holder.view.info_button.setOnClickListener {
            infoBUttonClicked(item)
        }
    }

    fun updateData(list: List<Shop>) {
        items = list.sortedBy { it.distance }
        notifyDataSetChanged()
    }

    fun addItem(shop: Shop) {
        val tempList = items
                .toMutableList()
                .also { it.add(shop) }
        tempList.sortBy { it.distance }
        items = tempList.toList()
        notifyDataSetChanged()
    }

    fun clearItems() {
        items = emptyList()
        notifyDataSetChanged()
    }
}

class ShopListItemVH(val view: View) : ViewHolder(view) {

    fun setViewHolder(item: Shop) {

        val openingHoursText: String =
                if (SundayShopping.isShoppingAllowed())
                    view.resources.getString(
                            R.string.opening_hours,
                            item.openHours.getForToday().toString()
                    )
                else view.resources.getString(R.string.shop_closed)

        view.shop_address.text = item.printName()
        if (item.distance == null) {
            view.distance_label.visibility = GONE
        } else {
            view.distance_label.text = item.distance.generateDistanceText(view.resources)
        }
        view.open_hours_label.text = openingHoursText
    }
}