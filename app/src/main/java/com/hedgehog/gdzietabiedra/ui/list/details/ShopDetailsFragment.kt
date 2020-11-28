package com.hedgehog.gdzietabiedra.ui.list.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hedgehog.gdzietabiedra.R
import kotlinx.android.synthetic.main.shop_details_fragment.*
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel

class ShopDetailsFragment : Fragment() {

    private val vm: ShopDetailsViewModel by viewModel(this, ShopDetailsViewModel::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        arguments?.getString("shopId")?.let {
            vm.openedFromList(it)
        }
        vm.shopData.observe(viewLifecycleOwner) {
            text_view.text = it.toString()
        }
        return inflater.inflate(R.layout.shop_details_fragment, container, false)
    }
}