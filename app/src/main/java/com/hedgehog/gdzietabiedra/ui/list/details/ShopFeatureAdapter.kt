package com.hedgehog.gdzietabiedra.ui.list.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.asvid.biedra.domain.*
import com.hedgehog.gdzietabiedra.R
import kotlinx.android.synthetic.main.shop_feature_item.view.*

class ShopFeatureAdapter(private val features: List<ShopFeature>) : RecyclerView.Adapter<ShopFeatureAdapter.ShopFeatureVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopFeatureVH {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.shop_feature_item, parent, false)
        return ShopFeatureVH(view)
    }

    override fun onBindViewHolder(holder: ShopFeatureVH, position: Int) {
        val item = features[position]
        holder.setViewHolder(item)
    }

    override fun getItemCount(): Int = features.size

    class ShopFeatureVH(val view: View) : RecyclerView.ViewHolder(view) {

        private val featureMapping = mapOf(
                ShopFeature.Bakery to R.drawable.ic_baseline_cake_24,
                ShopFeature.Relax to R.drawable.ic_baseline_restaurant_menu_24,
                ShopFeature.Atm to R.drawable.ic_baseline_local_atm_24,
                ShopFeature.CardPayment to R.drawable.ic_baseline_credit_card_24,
                ShopFeature.TaxFreeShopping to R.drawable.ic_baseline_money_off_24,
                ShopFeature.EuroAccepted to R.drawable.ic_baseline_euro_24,
                ShopFeature.NewShop to R.drawable.ic_baseline_fiber_new_24,
        )

        fun setViewHolder(item: ShopFeature) {
            view.shop_feature_name_view.text = getFeatureName(item)
            view.shop_feature_icon_view.setImageResource(getIconForFeature(item))
        }

        private fun getFeatureName(item: ShopFeature): String {
            return when(item){
                ShopFeature.Bakery -> view.resources.getString(R.string.bakery)
                ShopFeature.Relax -> view.resources.getString(R.string.relax)
                ShopFeature.Atm -> view.resources.getString(R.string.atm)
                ShopFeature.CardPayment -> view.resources.getString(R.string.card_payment)
                ShopFeature.TaxFreeShopping -> view.resources.getString(R.string.tax_free)
                ShopFeature.EuroAccepted -> view.resources.getString(R.string.euro_accepted)
                ShopFeature.NewShop -> view.resources.getString(R.string.new_shop)
            }
        }

        private fun getIconForFeature(item: ShopFeature) =
                featureMapping[item] ?: R.drawable.ic_baseline_info_black_24
    }
}
