package com.hedgehog.gdzietabiedra.ui.list

import android.R.string
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hedgehog.gdzietabiedra.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener.Builder
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_list.shop_list_view
import kotlinx.android.synthetic.main.fragment_list.shop_search_view
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel

class ListFragment : Fragment() {

  private val vm: ListViewModel by viewModel(this, ListViewModel::class.java)

  lateinit var shopsAdapter: ShopListAdapter

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_list, container, false)
    vm.loadData()
    vm.permissionRequest.observeForever {
      requestLocationPermission(it)
    }
    vm.shopList.observeForever{
      shopsAdapter.updateData(it)
    }
    return root
  }

  override fun onResume() {
    setupShopSearch()
    setupShopList()
    super.onResume()
  }

  private fun setupShopList() {
    val linearLayoutManager = LinearLayoutManager(context)
    shopsAdapter = ShopListAdapter {
      vm.shopListItemClicked(it)
    }
    shop_list_view.adapter = shopsAdapter
    shop_list_view.layoutManager = linearLayoutManager
    shop_list_view.addItemDecoration(MarginItemDecoration(
        resources.getDimension(R.dimen.default_padding).toInt()))
  }

  private fun setupShopSearch() {
    shop_search_view.setOnQueryTextListener(object : OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean {
        vm.shopSearchQuerySubmited(query)
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        vm.shopSearchQueryChanged(newText)
        return true
      }
    })
  }

  private fun requestLocationPermission(permission: String) {
    val dialogPermissionListener: PermissionListener = Builder
        .withContext(context)
        .withTitle("Location")
        .withMessage("To calculate distance to nearest shop app needs to know where you are.")
        .withButtonText(string.ok)
        .withIcon(R.mipmap.ic_launcher)
        .build()

    Dexter.withContext(context)
        .withPermission(permission)
        .withListener(dialogPermissionListener).check()
  }
}