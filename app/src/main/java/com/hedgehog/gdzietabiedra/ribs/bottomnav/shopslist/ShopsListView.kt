package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.widget.LinearLayout
import com.hedgehog.gdzietabiedra.domain.Shop
import kotlinx.android.synthetic.main.shoplist_rib.view.*

/**
 * Top level view for {@link ShopsListBuilder.ShopsListScope}.
 */
class ShopsListView @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyle: Int = 0) : LinearLayout(context,
        attrs,
        defStyle), ShopsListInteractor.ShopsListPresenter {

    private val adapter: ShopListAdapter = ShopListAdapter()

    override fun setView() {
        shops_list.adapter = adapter
        shops_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun populateList(shops: Collection<Shop>) {
        adapter.updateData(shops.toList())
    }

}
