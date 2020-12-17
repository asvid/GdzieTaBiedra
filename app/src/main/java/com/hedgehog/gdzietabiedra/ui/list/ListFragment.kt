package com.hedgehog.gdzietabiedra.ui.list

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.asvid.biedra.domain.Shop
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

        vm.viewState.observe(viewLifecycleOwner) {
            when (it) {
                RequestLocationPermission -> requestLocationPermission()
                LocationPermissionDenied -> {
                    displayLocationPermissionSnackbar()
                    displayNoLocationInfo()
                }
                NoAvailableLocation -> displayNoLocationInfo()
                LoadingShops -> displayLoading()
                ShopsLoaded -> displayLoadedShops()
                NoShopsAvailable -> displayNoShopsAvailable()
                ErrorLoadingShops -> displayErrorLoadingShops()
                is OpenShopDetails -> openShopDetails(it.shop)
                is OpenMapWithShop -> openMapWithShop(it.shop)
            }
        }
        vm.shopList.observe(viewLifecycleOwner) {
            shopsAdapter.updateData(it)
        }
        setHasOptionsMenu(true)
        return root
    }

    private fun openMapWithShop(shop: Shop) {
        val bundle = bundleOf("shopId" to shop.id)
        findNavController().navigate(R.id.action_navigation_list_to_navigation_map, bundle)
    }

    private fun openShopDetails(shop: Shop) {
        val bundle = bundleOf("shopId" to shop.id)
        findNavController().navigate(R.id.action_navigation_list_to_navigation_shop_details, bundle)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val mSearchMenuItem: MenuItem = menu.findItem(R.id.search)
        val searchView = mSearchMenuItem.actionView as SearchView
        setupShopSearch(searchView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_view_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> focusSearchInput(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun focusSearchInput(item: MenuItem) {
        val searchView = item.actionView as SearchView
        searchView.isIconifiedByDefault = true
        searchView.isFocusable = true
        searchView.isIconified = false
        searchView.requestFocusFromTouch()
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                searchView.onActionViewExpanded()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                searchView.clearFocus()
                return true
            }
        })
    }

    private fun displayErrorLoadingShops() {
        displayText(R.string.error_loading_shops)
    }

    private fun displayNoShopsAvailable() {
        displayText(R.string.no_shops_found_use_search)
        shopsAdapter.clearItems()
    }

    private fun displayLoadedShops() {
        list_info_text_view.visibility = GONE
        shop_list_layout.visibility = VISIBLE
        shop_list_layout.isRefreshing = false
    }

    private fun displayLoading() {
        list_info_text_view.visibility = GONE
        shop_list_layout.visibility = VISIBLE
        shop_list_layout.isRefreshing = true
    }

    private fun displayNoLocationInfo() {
        displayText(R.string.turn_on_location)
    }

    private fun displayText(resId: Int) {
        list_info_text_view.setText(resId)
        list_info_text_view.visibility = VISIBLE
        shop_list_layout.visibility = GONE
        shop_list_layout.isRefreshing = false
    }

    override fun onStart() {
        setupShopList()
        vm.loadData()
        super.onStart()
    }

    private fun setupShopList() {
        val linearLayoutManager = LinearLayoutManager(context)
        shopsAdapter = ShopListAdapter(
                {
                    vm.shopListItemMapButtonClicked(it)
                },
                {
                    vm.shopListItemInfoButtonClicked(it)
                }
        )
        shop_list_view.adapter = shopsAdapter
        shop_list_view.layoutManager = linearLayoutManager
        shop_list_view.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))

        shop_list_layout.setOnRefreshListener {
            vm.loadData()
        }
    }

    private fun setupShopSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                vm.shopSearchByQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.shopSearchByQuery(newText)
                return false
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