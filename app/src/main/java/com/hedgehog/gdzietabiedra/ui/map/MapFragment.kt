package com.hedgehog.gdzietabiedra.ui.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hedgehog.gdzietabiedra.R.layout
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import kotlinx.android.synthetic.main.fragment_map.map_navigation_button
import kotlinx.android.synthetic.main.fragment_map.map_view
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class MapFragment : Fragment() {

  private val vm: MapViewModel by ViewModelCompat.viewModel(this, MapViewModel::class.java)

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    setupOpenNavigation()
    return inflater.inflate(layout.fragment_map, container, false)
  }

  override fun onResume() {
    setupMap()
    setupNavigationButton()
    super.onResume()
  }

  private fun setupNavigationButton() {
    vm.showNavButton.observe(viewLifecycleOwner) {
      map_navigation_button.visibility = if (it) VISIBLE else GONE
    }
    map_navigation_button.setOnClickListener {
      vm.navigationButtonClicked()
    }
  }

  private fun setupMap() {
    map_view.onCreate(null)
    map_view.getMapAsync {
      Timber.d("map is loaded")
      val googleMapProvider = GoogleMapProvider.create(it, requireContext())
      map_view.onResume()
      vm.mapLoaded(googleMapProvider)
    }
  }

  private fun setupOpenNavigation() {
    vm.openNavigation.observe(viewLifecycleOwner) { shop ->
      val intent = Intent(Intent.ACTION_VIEW)
      intent.data = Uri.parse("geo:" + shop.location.lat + "," +
          shop.location.lng + "?q=" + shop.address)
      requireContext().startActivity(intent)
    }
  }
}