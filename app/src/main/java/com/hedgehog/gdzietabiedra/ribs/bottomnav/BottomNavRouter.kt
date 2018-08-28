package com.hedgehog.gdzietabiedra.ribs.bottomnav

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link BottomNavBuilder.BottomNavScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class BottomNavRouter(
    view: BottomNavView,
    interactor: BottomNavInteractor,
    component: BottomNavBuilder.Component) : ViewRouter<BottomNavView, BottomNavInteractor, BottomNavBuilder.Component>(view, interactor, component)
