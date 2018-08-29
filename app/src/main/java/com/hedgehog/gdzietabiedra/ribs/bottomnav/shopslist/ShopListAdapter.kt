package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.domain.ShopModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.Collections

class ShopListAdapter(
    private val observable: Observable<List<ShopModel>>
) : RecyclerView.Adapter<ShopListItemVH>() {

  val itemClickSubject = PublishSubject.create<ShopModel>()
  private var currentList: List<ShopModel> = Collections.emptyList()

  init {
    this.observable
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { items ->
          this.currentList = items
          this.notifyDataSetChanged()
        }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListItemVH {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_item,
        parent, false)
    return ShopListItemVH(view)
  }

  override fun getItemCount(): Int {
    return currentList.size
  }

  override fun onBindViewHolder(holder: ShopListItemVH, position: Int) {
    val item = currentList[position]
    holder.setViewHolder(item)
    holder.view.setOnClickListener {
      itemClickSubject.onNext(item)
    }
  }

}

class ShopListItemVH(val view: View) : ViewHolder(view) {

  fun setViewHolder(item: ShopModel) {
    view.findViewById<TextView>(R.id.shop_address).text = item.address
    view.findViewById<TextView>(R.id.shop_address).text = item.address
    view.findViewById<TextView>(R.id.shop_address).text = item.address
  }
}