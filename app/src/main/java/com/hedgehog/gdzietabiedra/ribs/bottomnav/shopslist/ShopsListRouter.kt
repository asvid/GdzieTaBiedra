package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ShopsListBuilder.ShopsListScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ShopsListRouter(
    view: ShopsListView,
    interactor: ShopsListInteractor,
    component: ShopsListBuilder.Component) : ViewRouter<ShopsListView, ShopsListInteractor, ShopsListBuilder.Component>(view, interactor, component)
