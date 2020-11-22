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
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        vm.viewState.observe(viewLifecycleOwner) {
            // TODO: 21/11/2020 move location permission handling to upper level?
            when (it) {
                RequestLocationPermission -> requestLocationPermission()
                LocationPermissionDenied -> {
                    displayLocationPermissionSnackbar()
                    displayNoShopsAvailable()
                }
                NoAvailableLocation -> displayNoLocationInfo()
                LoadingShops -> displayLoading()
                ShopsLoaded -> displayLoadedShops()
                NoShopsAvailable -> displayNoShopsAvailable()
                ErrorLoadingShops -> displayErrorLoadingShops()
            }
        }
        vm.shopList.observe(viewLifecycleOwner) {
            shopsAdapter.updateData(it)
        }
        setHasOptionsMenu(true)
        return root
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
            R.id.info -> findNavController().navigate(R.id.action_navigation_list_to_navigation_info)
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
        displayText("Error while loading shops")
    }

    private fun displayNoShopsAvailable() {
        displayText("No shops around, use search bar or map")
        shopsAdapter.clearItems()
    }

    private fun displayLoadedShops() {
        progress_view.visibility = GONE
        empty_list_view.visibility = GONE
    }

    private fun displayLoading() {
        progress_view.visibility = VISIBLE
        empty_list_view.visibility = GONE
    }

    private fun displayNoLocationInfo() {
        displayText("Please use search box")
    }

    private fun displayText(text: String) {
        empty_list_view.text = text
        empty_list_view.visibility = VISIBLE
        progress_view.visibility = GONE
    }

    override fun onStart() {
        setupShopList()
        vm.loadData()
        super.onStart()
    }

    private fun setupShopList() {
        val linearLayoutManager = LinearLayoutManager(context)
        shopsAdapter = ShopListAdapter {
//          vm.shopListItemClicked(it)
            val bundle = bundleOf("shopId" to it.id)
            findNavController().navigate(R.id.action_navigation_list_to_navigation_map, bundle)
        }
        shop_list_view.adapter = shopsAdapter
        shop_list_view.layoutManager = linearLayoutManager
        shop_list_view.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_padding).toInt()))
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