package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdog
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.domain.Shop
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent.ShopSelected
import com.hedgehog.gdzietabiedra.utils.async
import com.hedgehog.gdzietabiedra.utils.subscribeWithErrorLogging
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ShopsListScope].
 *
 * TODO describe the logic of this scope.
 */
const val RANGE: Double = 0.05

@RibInteractor
class ShopsListInteractor :
    BaseInteractor<ShopsListInteractor.ShopsListPresenter, ShopsListRouter>() {

  @Inject
  lateinit var presenter: ShopsListPresenter
  @Inject
  lateinit var shopsService: ShopService
  @Inject
  lateinit var locationWatchdog: LocationWatchdog
  @Inject
  lateinit var listener: ShopListListener

  private lateinit var compositeDisposable: CompositeDisposable

  private var currentUserLocation: Position? = null

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    presenter.setView()

    locationWatchdog.getLocation()
        .async()
        .toFlowable(LATEST)
        .flatMap {
          presenter.clearList()
          currentUserLocation = it
          shopsService.getShopsInRange(it, RANGE)
        }
        .subscribeWithErrorLogging {
          presenter.addToList(it)
        }
        .addToDisposables()

    presenter.listItemClicked()
        .async()
        .subscribeWithErrorLogging {
          listener.onShopSelected(ShopSelected(shop = it))
        }
        .addToDisposables()

    presenter.observeSearch()
        .async()
        .doOnNext { presenter.clearList() }
        .flatMap {
          if (it.isBlank()) {
            shopsService
                .getShopsInRange(currentUserLocation, RANGE)
                .toObservable()
          } else {
            shopsService
                .getShopsByAddress(it, currentUserLocation)
                .toObservable()
          }
        }
        .subscribeWithErrorLogging {
          presenter.addToList(it)
        }
        .addToDisposables()
  }

  interface ShopsListPresenter {

    fun setView()
    fun populateList(shops: Collection<Shop>)
    fun addToList(shop: Shop)
    fun listItemClicked(): Subject<Shop>
    fun showToast(shop: Shop)
    fun observeSearch(): Observable<String>
    fun clearList()
  }
}
