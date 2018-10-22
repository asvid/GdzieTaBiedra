package com.hedgehog.gdzietabiedra.ribs

import android.view.View.GONE
import android.view.View.VISIBLE
import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapRouter
import com.hedgehog.gdzietabiedra.ribs.bottomnav.settings.SettingsBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.settings.SettingsRouter
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopsListBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopsListRouter
import com.uber.rib.core.ViewRouter
import timber.log.Timber

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    private val view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val bottomNavBuilder: BottomNavBuilder,
    private val shoplistBuilder: ShopsListBuilder,
    private val mapBuilder: MapBuilder,
    private val settingsBuilder: SettingsBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  fun attachBottomNav() {
    Timber.d("attach bottom nav")
    val bottomNavRouter = bottomNavBuilder.build(view.viewNavigationLayout())
    attachChild(bottomNavRouter)
    view.viewNavigationLayout().addView(bottomNavRouter.view)
  }

  private var shopsListRouter: ShopsListRouter? = null
  private var mapRouter: MapRouter? = null
  private var settingsRouter: SettingsRouter? = null

  fun attachShopslist() {
    Timber.d("attach shops list")
    shopsListRouter = shoplistBuilder.build(view.viewContent())
    attachChild(shopsListRouter)
    view.viewContent().addView(shopsListRouter?.view)
  }

  fun detachShopslist() {
    if (shopsListRouter != null) {
      detachChild(shopsListRouter)
      view.viewContent().removeView(shopsListRouter?.view)
      shopsListRouter = null
    }
  }

  fun attachMap() {
    Timber.d("attach map")
    mapRouter?.let {
      mapRouter?.view?.visibility = VISIBLE
      return
    }
    mapRouter = mapBuilder.build(view.viewContent())
    attachChild(mapRouter)
    view.viewContent().addView(mapRouter?.view)
  }

  fun detachMap() {
    mapRouter?.view?.visibility = GONE
  }

  fun attachSettings() {
    Timber.d("attach settings")
    settingsRouter = settingsBuilder.build(view.viewContent())
    attachChild(settingsRouter)
    view.viewContent().addView(settingsRouter?.view)
  }

  fun detachSettings() {
    if (settingsRouter != null) {
      detachChild(settingsRouter)
      view.viewContent().removeView(settingsRouter?.view)
      settingsRouter = null
    }
  }

}
