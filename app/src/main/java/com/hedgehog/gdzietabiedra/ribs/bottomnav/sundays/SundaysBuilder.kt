package com.hedgehog.gdzietabiedra.ribs.bottomnav.sundays

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
 * Builder for the {@link SundaysScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class SundaysBuilder(dependency: ParentComponent) : ViewBuilder<SundaysView, SundaysRouter, SundaysBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [SundaysRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [SundaysRouter].
   */
  fun build(parentViewGroup: ViewGroup): SundaysRouter {
    val view = createView(parentViewGroup)
    val interactor = SundaysInteractor()
    val component = DaggerSundaysBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.sundaysRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SundaysView? {
    return inflater.inflate(R.layout.rib_sundays, parentViewGroup,
        false) as SundaysView
  }

  interface ParentComponent

  @dagger.Module
  abstract class Module {

    @SundaysScope
    @Binds
    internal abstract fun presenter(view: SundaysView): SundaysInteractor.SundaysPresenter

    @dagger.Module
    companion object {

      @SundaysScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: SundaysView,
          interactor: SundaysInteractor): SundaysRouter {
        return SundaysRouter(view, interactor, component)
      }
    }
  }

  @SundaysScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<SundaysInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: SundaysInteractor): Builder

      @BindsInstance
      fun view(view: SundaysView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun sundaysRouter(): SundaysRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class SundaysScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class SundaysInternal
}
