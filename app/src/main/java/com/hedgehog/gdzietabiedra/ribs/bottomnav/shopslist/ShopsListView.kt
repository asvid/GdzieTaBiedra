package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.Toast
import com.hedgehog.gdzietabiedra.R
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.utils.views.MarginItemDecoration
import com.jakewharton.rxbinding3.widget.queryTextChanges
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.rib_shoplist.view.shop_search_view
import kotlinx.android.synthetic.main.rib_shoplist.view.shops_list

/**
 * Top level view for {@link ShopsListBuilder.ShopsListScope}.
 */
class ShopsListView @JvmOverloads constructor(context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), ShopsListInteractor.ShopsListPresenter {

  override fun addToList(shop: Shop) {
    adapter.addItem(shop)
  }

  override fun clearList() {
    adapter.clearItems()
  }

  private val searchSubject = PublishSubject.create<String>()

  override fun showToast(shop: Shop) {
    Toast.makeText(context, "item with shop: ${shop.address} was clicked", Toast.LENGTH_LONG).show()
  }

  override fun listItemClicked(): Subject<Shop> = adapter.itemClickSubject

  private val adapter: ShopListAdapter = ShopListAdapter()

  override fun setView() {
    val linearLayoutManager = LinearLayoutManager(context)

    shops_list.adapter = adapter
    shops_list.layoutManager = linearLayoutManager
    shops_list.addItemDecoration(MarginItemDecoration(
        resources.getDimension(R.dimen.default_padding).toInt()))

    shop_search_view.queryTextChanges()
        .map {
          it.toString()
        }
        .subscribe(searchSubject)
  }

  override fun observeSearch() = searchSubject

  override fun populateList(shops: Collection<Shop>) {
    adapter.updateData(shops.toList())
  }

  override fun displayLocationInfo() {
    Snackbar.make(this, R.string.turn_on_location, Snackbar.LENGTH_LONG).show()
  }
}
