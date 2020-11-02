package com.hedgehog.gdzietabiedra.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.asvid.biedra.domain.Shop
import com.github.asvid.biedra.domain.SundayShopping
import com.github.asvid.biedra.domain.getForToday
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.utils.round
import java.util.*
import kotlin.math.roundToInt

class ShopListAdapter(
        val itemClicked: (Shop) -> Unit
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
        holder.view.setOnClickListener {
            itemClicked(item)
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
                    item.openHours.getForToday().toString()
                else view.resources.getString(R.string.shop_closed)

        view.findViewById<TextView>(R.id.shop_address).text = item.address.toString()
        view.findViewById<TextView>(R.id.shop_distance).text = generateDistanceText(item.distance)
        view.findViewById<TextView>(R.id.shop_open_hours).text = openingHoursText
    }

    private fun generateDistanceText(distance: Double?): CharSequence {
        return when {
            distance == null -> ""
            distance > 5000 -> "${(distance / 1000).roundToInt()} km"
            distance > 1000 -> "${(distance / 1000).round(2)} km"
            else -> "${distance.roundToInt()} m"
        }
    }
}