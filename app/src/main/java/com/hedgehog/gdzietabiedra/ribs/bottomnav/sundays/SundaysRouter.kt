package com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SundaysBuilder.SundaysScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SundaysRouter(
    view: SundaysView,
    interactor: SundaysInteractor,
    component: SundaysBuilder.Component) : ViewRouter<SundaysView, SundaysInteractor, SundaysBuilder.Component>(view, interactor, component)
