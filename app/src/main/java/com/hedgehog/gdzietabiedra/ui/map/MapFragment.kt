package com.hedgehog.gdzietabiedra.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hedgehog.gdzietabiedra.R.layout
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import kotlinx.android.synthetic.main.fragment_map.map_view
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import timber.log.Timber

class MapFragment : Fragment() {

  private val vm: MapViewModel by ViewModelCompat.viewModel(this, MapViewModel::class.java)

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(layout.fragment_map, container, false)
  }

  override fun onResume() {
    setupMap()
    super.onResume()
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
}