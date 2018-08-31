package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Flowable

/**
 * Top level view for {@link ShopsListBuilder.ShopsListScope}.
 */
class ShopsListView @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyle: Int = 0) : LinearLayout(context,
        attrs,
        defStyle), ShopsListInteractor.ShopsListPresenter {

    lateinit var adapter: ShopListAdapter

    override fun setView() {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun populateList(shops: Flowable<Shop>) {

        adapter = ShopListAdapter(shops.toObservable())
    }

}
