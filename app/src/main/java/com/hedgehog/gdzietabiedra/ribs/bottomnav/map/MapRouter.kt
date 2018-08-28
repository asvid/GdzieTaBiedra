package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MapBuilder.MapScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MapRouter(
    view: MapView,
    interactor: MapInteractor,
    component: MapBuilder.Component) : ViewRouter<MapView, MapInteractor, MapBuilder.Component>(view, interactor, component)
