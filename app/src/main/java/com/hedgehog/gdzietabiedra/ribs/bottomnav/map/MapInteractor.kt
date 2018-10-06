package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import com.github.asvid.biedra.domain.Position
import com.google.android.gms.maps.GoogleMap
import com.hedgehog.gdzietabiedra.appservice.LocationService
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import com.hedgehog.gdzietabiedra.appservice.map.IMapProvider
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MapScope].
 *
 */
@RibInteractor
class MapInteractor : BaseInteractor<MapInteractor.MapPresenter, MapRouter>() {

  @Inject
  lateinit var presenter: MapPresenter
  @Inject
  lateinit var locationService: LocationService

  lateinit var mapProvider: IMapProvider

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    presenter.initView().zipWith(
        locationService.getLocation(),
        BiFunction<GoogleMap, Position, Any> { googleMap, position ->
          mapProvider = GoogleMapProvider.create(googleMap)
          mapProvider.goToPosition(position)
        }).subscribe().addToDisposables()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MapPresenter {

    fun initView(): Single<GoogleMap>
  }
}
