package com.hedgehog.gdzietabiedra.ribs

import android.view.View.GONE
import android.view.View.VISIBLE
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavRouter
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapRouter
import com.hedgehog.gdzietabiedra.ribs.bottomnav.settings.SettingsBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.settings.SettingsRouter
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopsListBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopsListRouter
import com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays.SundaysBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays.SundaysRouter
import com.hedgehog.gdzietabiedra.ribs.splash.SplashBuilder
import com.hedgehog.gdzietabiedra.ribs.splash.SplashRouter
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
    private val settingsBuilder: SettingsBuilder,
    private val sundaysBuilder: SundaysBuilder,
    private val splashBuilder: SplashBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private var bottomNavRouter: BottomNavRouter? = null
  private var shopsListRouter: ShopsListRouter? = null
  private var mapRouter: MapRouter? = null
  private var settingsRouter: SettingsRouter? = null
  private var splashRouter: SplashRouter? = null
  private var sundaysRouter: SundaysRouter? = null

  fun attachBottomNav() {
    Timber.d("attach bottom nav")
    bottomNavRouter = bottomNavBuilder.build(view.viewNavigationLayout())
    attachChild(bottomNavRouter)
    view.viewNavigationLayout().addView(bottomNavRouter?.view)
  }

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

  /**
   * Builds [com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapView] if it's not already build and shows is
   * */
  fun attachMap() {
    Timber.d("attach map")
    bottomNavRouter?.view?.selectedItemId = R.id.navigation_map
    mapRouter?.let {
      mapRouter?.view?.visibility = VISIBLE
      return
    }
    mapRouter = mapBuilder.build(view.viewContent())
    attachChild(mapRouter)
    view.viewContent().addView(mapRouter?.view)
  }

  /**
   * Builds [com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapView] without showing it
   * */
  fun attachMapHidden() {
    Timber.d("attach map")
    mapRouter = mapBuilder.build(view.viewContent())
    attachChild(mapRouter)
    mapRouter?.view?.visibility = GONE
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

  fun attachSundays() {
    Timber.d("attach sundays")
    sundaysRouter = sundaysBuilder.build(view.viewContent())
    attachChild(sundaysRouter)
    view.viewContent().addView(sundaysRouter?.view)
  }

  fun detachSundays() {
    if (sundaysRouter != null) {
      detachChild(sundaysRouter)
      view.viewContent().removeView(sundaysRouter?.view)
      sundaysRouter = null
    }
  }

  fun attachSplashScreen() {
    splashRouter = splashBuilder.build(view.viewContent())
    attachChild(splashRouter)
    view.viewContent().addView(splashRouter?.view)
  }

  fun detachSplashScreen() {
    if (splashRouter != null) {
      detachChild(splashRouter)
      view.viewContent().removeView(splashRouter?.view)
      splashRouter = null
    }
  }
}
