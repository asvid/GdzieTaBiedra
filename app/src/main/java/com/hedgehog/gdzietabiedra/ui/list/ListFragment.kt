package com.hedgehog.gdzietabiedra.ui.list

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hedgehog.gdzietabiedra.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_list.*
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
        vm.permissionRequest.observe(viewLifecycleOwner) {
            when (it) {
                RequestLocationPermission -> requestLocationPermission()
                LocationPermissionDenied -> displayLocationPermissionSnackbar()
                NoAvailableLocation -> Snackbar.make(
                        root, "no location available", Snackbar.LENGTH_SHORT).show()
            }
        }
        vm.shopList.observe(viewLifecycleOwner) {
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

    private fun requestLocationPermission() {
        Dexter.withContext(context)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        vm.locationPermissionGranted()
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        vm.locationPermissionDenied()
                    }

                    override fun onPermissionRationaleShouldBeShown(permRequest: PermissionRequest?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }
                })
                .check()
    }

    private fun displayLocationPermissionSnackbar() {
        Snackbar.make(shop_list_view, R.string.location_permissions_info, Snackbar.LENGTH_LONG)
                .setAction(R.string.settings) {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", context?.packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
                .setActionTextColor(Color.YELLOW)
                .show()
    }
}