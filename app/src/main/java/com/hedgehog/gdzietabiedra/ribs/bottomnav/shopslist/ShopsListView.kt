package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.shoplist_rib.view.shops_list

/**
 * Top level view for {@link ShopsListBuilder.ShopsListScope}.
 */
class ShopsListView @JvmOverloads constructor(context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0) : LinearLayout(context,
    attrs,
    defStyle), ShopsListInteractor.ShopsListPresenter {

  override fun showToast(shop: Shop) {
    Toast.makeText(context, "item with shop: ${shop.address} was clicked", Toast.LENGTH_LONG).show()
  }

  override fun listItemClicked(): Subject<Shop> = adapter.itemClickSubject

  private val adapter: ShopListAdapter = ShopListAdapter()

  override fun setView() {
    val linearLayoutManager = LinearLayoutManager(context)
    val dividerItemDecoration = DividerItemDecoration(context,
        linearLayoutManager.orientation)

    shops_list.adapter = adapter
    shops_list.addItemDecoration(dividerItemDecoration)
    shops_list.layoutManager = linearLayoutManager
  }

  override fun populateList(shops: Collection<Shop>) {
    adapter.updateData(shops.toList())
  }

}
