package com.hedgehog.gdzietabiedra.ribs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdog
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.BottomNavInteractor
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapEvent
import com.hedgehog.gdzietabiedra.ribs.bottomnav.settings.SettingsBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopListListener.ShopListEvent.ShopSelected
import com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist.ShopsListBuilder
import com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays.SundaysBuilder
import com.hedgehog.gdzietabiedra.ribs.splash.SplashBuilder
import com.hedgehog.gdzietabiedra.ribs.splash.SplashEvent
import com.hedgehog.gdzietabiedra.ribs.splash.SplashEvent.AllPermissionsGranted
import com.hedgehog.gdzietabiedra.ribs.splash.SplashListener
import com.hedgehog.gdzietabiedra.utils.analytics.Analytics
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.karumi.dexter.DexterBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link RootScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RootBuilder(
    dependency: ParentComponent
) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RootRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RootRouter].
   */
  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.rootRouter()
  }

  override fun inflateView(inflater: LayoutInflater,
                           parentViewGroup: ViewGroup): RootView? {
    return inflater.inflate(R.layout.rib_root, parentViewGroup, false) as RootView
  }

  interface ParentComponent {
    fun shopServices(): ShopService
    fun locationService(): LocationWatchdog
    fun dexter(): DexterBuilder.Permission
    fun analytics(): Analytics
  }

  @dagger.Module
  abstract class Module {

    @RootScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

    @dagger.Module
    companion object {

      @RootScope
      @Provides
      @JvmStatic
      internal fun provideNavigationListener(
          rootInteractor: RootInteractor): BottomNavInteractor.Listener {
        return rootInteractor.NavigationListener()
      }

      @RootScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RootView,
          interactor: RootInteractor): RootRouter {
        return RootRouter(view, interactor, component,
            BottomNavBuilder(component),
            ShopsListBuilder(component), MapBuilder(component),
            SettingsBuilder(component),
            SundaysBuilder(component),
            SplashBuilder(component)
        )
      }

      @RootScope
      @Provides
      @JvmStatic
      fun shopListRibRelay(): PublishRelay<ShopListEvent> = PublishRelay.create<ShopListEvent>()

      @RootScope
      @Provides
      @JvmStatic
      fun mapRibRelay(): PublishRelay<MapEvent> = PublishRelay.create<MapEvent>()

      @RootScope
      @Provides
      @JvmStatic
      fun splashRelay(): BehaviorRelay<SplashEvent> = BehaviorRelay.create<SplashEvent>()

      @RootScope
      @Provides
      @JvmStatic
      internal fun listener(
          shopListRibRelay: PublishRelay<ShopListEvent>
      ): ShopListListener {
        return object : ShopListListener {
          override fun onShopSelected(shopSelected: ShopSelected) {
            shopListRibRelay.accept(shopSelected)
          }
        }
      }

      @RootScope
      @Provides
      @JvmStatic
      internal fun splashListener(
          splashRelay: BehaviorRelay<SplashEvent>
      ): SplashListener {
        return object : SplashListener {
          override fun allPermissionsGranted() {
            Timber.d("Root builder: allPermissionsGranted")
            splashRelay.accept(AllPermissionsGranted)
          }
        }
      }
    }
  }

  @RootScope
  @dagger.Component(
      modules = [Module::class],
      dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<RootInteractor>,
      BuilderComponent,
      BottomNavBuilder.ParentComponent,
      ShopsListBuilder.ParentComponent,
      MapBuilder.ParentComponent,
      SettingsBuilder.ParentComponent,
      SundaysBuilder.ParentComponent,
      SplashBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {

      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun rootRouter(): RootRouter
  }

  @Scope
  @Retention
  internal annotation class RootScope

  @Qualifier
  @Retention
  internal annotation class RootInternal
}
