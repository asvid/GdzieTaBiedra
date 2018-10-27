package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.appservice.LocationService
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.jakewharton.rxrelay2.PublishRelay
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link MapScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class MapBuilder(
    dependency: ParentComponent) : ViewBuilder<MapView, MapRouter, MapBuilder.ParentComponent>(
    dependency) {

  /**
   * Builds a new [MapRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MapRouter].
   */
  fun build(parentViewGroup: ViewGroup): MapRouter {
    val view = createView(parentViewGroup)
    val interactor = MapInteractor()
    val component = DaggerMapBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.mapRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MapView? {
    return inflater.inflate(R.layout.rib_map, parentViewGroup, false) as MapView
  }

  interface ParentComponent {
    fun locationService(): LocationService
    fun shopServices(): ShopService
    fun mapEvents(): PublishRelay<MapEvent>
  }

  @dagger.Module
  abstract class Module {

    @MapScope
    @Binds
    internal abstract fun presenter(view: MapView): MapInteractor.MapPresenter

    @dagger.Module
    companion object {

      @MapScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: MapView,
          interactor: MapInteractor): MapRouter {
        return MapRouter(view, interactor, component)
      }
    }
  }

  @MapScope
  @dagger.Component(modules = [Module::class],
      dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<MapInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: MapInteractor): Builder

      @BindsInstance
      fun view(view: MapView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun mapRouter(): MapRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class MapScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class MapInternal
}
