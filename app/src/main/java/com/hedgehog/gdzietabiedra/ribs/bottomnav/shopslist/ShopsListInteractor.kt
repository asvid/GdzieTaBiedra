package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdog
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent.ShopSelected
import com.hedgehog.gdzietabiedra.utils.analytics.Analytics
import com.hedgehog.gdzietabiedra.utils.analytics.EventType
import com.hedgehog.gdzietabiedra.utils.analytics.EventType.Event.EventName.*
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
 * Coordinates Business Logic for [ShopsListBuilder.ShopsListScope].
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
  @Inject
  lateinit var analytics: Analytics

  private lateinit var compositeDisposable: CompositeDisposable

  private var currentUserLocation: Position? = null

  override fun getRibName(): String = "Shop List"

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    presenter.setView()

    fillListBasedOnLocation()
    handleListItemClicks()
    handleSearch()
    handleLocationServiceWarningDisplay()

    analytics.log(EventType.Screen(getRibName()))
  }

  private fun handleLocationServiceWarningDisplay() {
    locationWatchdog.locationEnabledSubject()
        .async()
        .filter { !it }
        .subscribe {
          analytics.log(EventType.Event(SHOW_LOCATION_WARNING))
          presenter.displayLocationInfo()
        }.addToDisposables()
  }

  private fun handleSearch() {
    presenter.observeSearch()
        .async()
        .doOnNext { presenter.clearList() }
        .flatMap {
          analytics.log(EventType.Event(SEARCH_SHOP_LIST))
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

  private fun handleListItemClicks() {
    presenter.listItemClicked()
        .async()
        .subscribeWithErrorLogging {
          analytics.log(EventType.Event(LIST_ITEM_CLICKED))
          listener.onShopSelected(ShopSelected(shop = it))
        }
        .addToDisposables()
  }

  private fun fillListBasedOnLocation() {
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
  }

  interface ShopsListPresenter {

    fun setView()
    fun populateList(shops: Collection<Shop>)
    fun addToList(shop: Shop)
    fun listItemClicked(): Subject<Shop>
    fun showToast(shop: Shop)
    fun observeSearch(): Observable<String>
    fun clearList()
    fun displayLocationInfo()
  }
}
