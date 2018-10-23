package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.appservice.LocationService
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.domain.Shop
import com.hedgehog.gdzietabiedra.utils.async
import com.uber.rib.core.BaseInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.Subject
import timber.log.Timber
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
  lateinit var locationService: LocationService

  private lateinit var compositeDisposable: CompositeDisposable

  private lateinit var currentUserLocation: Position

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    compositeDisposable = CompositeDisposable()
    presenter.setView()

    locationService.getLocation()
        .async()
        .toFlowable()
        .flatMap {
          currentUserLocation = it
          shopsService.getShopsInRange(it, RANGE)
        }
        .subscribeBy(
            onNext = {
              presenter.addToList(it)
            })
        .addToDisposables()


    presenter.listItemClicked()
        .async()
        .subscribeBy(
            onNext = {
              Timber.d("item clicked: $it")
              presenter.showToast(it)
            })
        .addToDisposables()

    presenter.observeSearch()
        .async()
        .doOnNext { presenter.clearList() }
        .switchIfEmpty {
          shopsService.getShopsInRange(currentUserLocation, RANGE)
        }
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
        .subscribeBy(
            onNext = {
              presenter.addToList(it)
            })
        .addToDisposables()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
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
