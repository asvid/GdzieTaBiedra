package com.hedgehog.gdzietabiedra.ribs.bottomnav

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.R
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
 * Builder for the {@link BottomNavScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class BottomNavBuilder(
    dependency: ParentComponent) :
    ViewBuilder<BottomNavView, BottomNavRouter, BottomNavBuilder.ParentComponent>(
        dependency) {

  /**
   * Builds a new [BottomNavRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [BottomNavRouter].
   */
  fun build(parentViewGroup: ViewGroup): BottomNavRouter {
    val view = createView(parentViewGroup)
    val interactor = BottomNavInteractor()
    val component = DaggerBottomNavBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.bottomnavRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): BottomNavView? {
    return inflater.inflate(R.layout.bottomnav_rib, parentViewGroup, false) as BottomNavView
  }

  interface ParentComponent {
    fun navigationListener(): BottomNavInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @BottomNavScope
    @Binds
    internal abstract fun presenter(view: BottomNavView): BottomNavInteractor.BottomNavPresenter

    @dagger.Module
    companion object {

      @BottomNavScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: BottomNavView,
          interactor: BottomNavInteractor): BottomNavRouter {
        return BottomNavRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @BottomNavScope
  @dagger.Component(modules = [Module::class],
      dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<BottomNavInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: BottomNavInteractor): Builder

      @BindsInstance
      fun view(view: BottomNavView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun bottomnavRouter(): BottomNavRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class BottomNavScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class BottomNavInternal
}
