package com.hedgehog.gdzietabiedra.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.adapters.ShopsAdapter
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop
import com.hedgehog.gdzietabiedra.utils.Database
import com.hedgehog.gdzietabiedra.utils.MessageEvent
import de.greenrobot.event.EventBus
import java.util.*
import kotlin.properties.Delegates

class ShopListFragment : Fragment() {

    internal var recycler: RecyclerView by Delegates.notNull()
    internal var mAdapter: ShopsAdapter by Delegates.notNull()
    internal var emptyText: TextView by Delegates.notNull()
    internal var searchInput: com.rey.material.widget.EditText by Delegates.notNull()

    internal var shops: MutableList<Shop> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun onEvent(event: MessageEvent) {
        if (event.type === MessageEvent.types.DATABASE_UPDATE) {
            shops.clear()
            shops.addAll(Database.listClosest)
            checkShopList()
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun checkShopList() {
        if (shops.size == 0) {
            emptyText.visibility = View.VISIBLE
        } else {
            emptyText.visibility = View.GONE
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list, container, false)
        shops.clear()
        shops.addAll(Database.listClosest)
        mAdapter = ShopsAdapter(shops)
        emptyText = view.findViewById(R.id.emptyText) as TextView

        checkShopList()

        recycler = view.findViewById(R.id.shopList) as RecyclerView

        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        val verticalDivider = ContextCompat.getDrawable(context, R.drawable.vertical_divider)
        divider.setDrawable(verticalDivider)
        recycler.addItemDecoration(divider)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = mAdapter

        searchInput = view.findViewById(R.id.searchInput) as com.rey.material.widget.EditText
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("zmiana", s.toString())
                shops.clear()
                shops.addAll(Database.getByFilter(s.toString()))
                mAdapter.notifyDataSetChanged()
                checkShopList()
                if (s == "") {
                    shops.clear()
                    shops.addAll(Database.listClosest)
                    mAdapter.notifyDataSetChanged()
                    checkShopList()
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        return view
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
