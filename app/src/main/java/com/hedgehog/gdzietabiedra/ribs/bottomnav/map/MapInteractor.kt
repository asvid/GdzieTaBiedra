package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import com.github.asvid.biedra.domain.Position
import com.google.android.gms.maps.GoogleMap
import com.hedgehog.gdzietabiedra.appservice.LocationService
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import com.hedgehog.gdzietabiedra.appservice.map.IMapProvider
import com.hedgehog.gdzietabiedra.appservice.map.ShopMarker
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
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
  @Inject
  lateinit var shopsService: ShopService

  private lateinit var mapProvider: IMapProvider

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    presenter.initView().zipWith(
        locationService.getLocation(),
        BiFunction<GoogleMap, Position, Position> { googleMap, position ->
          mapProvider = GoogleMapProvider.create(googleMap)
          mapProvider.goToPosition(position)
          position
        })
        .subscribeBy { position ->
          shopsService.getShopsInRange(position, 0.1)
              .subscribeBy { shops ->
                mapProvider.drawMarkers(shops.map {
                  ShopMarker(it.location, it)
                })
              }
          mapProvider.shopMarkerClicked()
              .subscribeBy {
                presenter.switchNavigationButton(true)
              }
          mapProvider.mapClicked()
              .subscribeBy {
                presenter.switchNavigationButton(false)
              }
          presenter.navigationButtonListener()
              .withLatestFrom(mapProvider.shopMarkerClicked(),
                  BiFunction<Any, ShopMarker, Any> { _, shopMarker ->
                    presenter.startNavigation(shopMarker.position)
                  }
              ).subscribe()
        }.addToDisposables()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MapPresenter {

    fun initView(): Single<GoogleMap>
    fun navigationButtonListener(): Observable<*>
    fun startNavigation(position: Position)
    fun switchNavigationButton(visible: Boolean)
  }
}
