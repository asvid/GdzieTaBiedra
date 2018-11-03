package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import com.hedgehog.gdzietabiedra.appservice.LocationWatchdog
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.appservice.map.MapProvider
import com.hedgehog.gdzietabiedra.appservice.map.ShopMarker
import com.hedgehog.gdzietabiedra.domain.Shop
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapEvent.ShopSelected
import com.hedgehog.gdzietabiedra.utils.analytics.Analytics
import com.hedgehog.gdzietabiedra.utils.async
import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.jakewharton.rxrelay2.PublishRelay
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
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
    lateinit var locationWatchdog: LocationWatchdog
    @Inject
    lateinit var shopsService: ShopService
    @Inject
    lateinit var mapEvents: PublishRelay<MapEvent>
    @Inject
    lateinit var analytics: Analytics

    private lateinit var mapProvider: MapProvider
    private val mapSubject = BehaviorSubject.create<MapProvider>()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        handleMapInit()
        handleMarkerClicks()
        handleMapClicks()
        handleMapMoved()
        handleNavigationClicked()
        handleShowingShopOnMap()
    }

    private fun handleShowingShopOnMap() {
        mapSubject
                .async()
                .concatMap { mapEvents }
                .flatMapCompletable {
                    when (it) {
                        is ShopSelected -> {
                            mapProvider.selectShop(it.shop)
                        }
                    }
                }
                .subscribeWithErrorLogging {
                    Timber.d("mapSubject")
                }
    }

    private fun handleMapInit() {
        presenter.initView()
                .doOnSuccess {
                    this.mapProvider = it
                    mapSubject.onNext(this.mapProvider)
                }
                .flatMapObservable {
                    locationWatchdog.getLocation()
                }
                .doOnNext {
                    this.mapProvider.goToPosition(it)
                }
                .toFlowable(LATEST)
                .flatMap {
                    shopsService.getShopsInRange(it, 0.1)
                }
                .subscribeWithErrorLogging {
                    mapProvider.drawMarker(
                            ShopMarker.create(it), false)
                }
                .addToDisposables()
    }

    private fun handleNavigationClicked() {
        mapSubject
                .async()
                .flatMap {
                    it.shopMarkerClicked()
                }
                .flatMap { shopMarker ->
                    presenter.navigationButtonListener()
                            .concatMap {
                                Observable.just(shopMarker)
                            }
                }
                .subscribeWithErrorLogging {
                    presenter.startNavigation(it.shop)
                }
                .addToDisposables()
    }

    private fun handleMapMoved() {
        mapSubject
                .async()
                .concatMap {
                    it.mapMoved()
                }
                .toFlowable(LATEST)
                .flatMap {
                    presenter.switchNavigationButton(false)
                    mapProvider.clearMap()
                    shopsService.getShopsInRange(it, 0.1)
                }
                .subscribeWithErrorLogging { shop ->
                    mapProvider.drawMarker(
                            ShopMarker.create(shop), false)
                }
                .addToDisposables()
    }

    private fun handleMapClicks() {
        mapSubject
                .async()
                .concatMap {
                    it.mapClicked()
                }
                .subscribeWithErrorLogging {
                    presenter.switchNavigationButton(false)
                }
                .addToDisposables()
    }

    private fun handleMarkerClicks() {
        mapSubject
                .async()
                .concatMap {
                    it.shopMarkerClicked()
                }
                .subscribeWithErrorLogging {
                    presenter.switchNavigationButton(true)
                }.addToDisposables()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface MapPresenter {

        fun initView(): Single<MapProvider>
        fun navigationButtonListener(): Observable<Unit>
        fun startNavigation(shop: Shop)
        fun switchNavigationButton(visible: Boolean)
    }
}
