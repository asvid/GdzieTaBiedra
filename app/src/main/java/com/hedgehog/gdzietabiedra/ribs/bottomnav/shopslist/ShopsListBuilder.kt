package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hedgehog.gdzietabiedra.R
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
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
 * Builder for the {@link ShopsListScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ShopsListBuilder(
        dependency: ParentComponent) : ViewBuilder<ShopsListView, ShopsListRouter, ShopsListBuilder.ParentComponent>(
        dependency) {

    /**
     * Builds a new [ShopsListRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [ShopsListRouter].
     */
    fun build(parentViewGroup: ViewGroup): ShopsListRouter {
        val view = createView(parentViewGroup)
        val interactor = ShopsListInteractor()
        val component = DaggerShopsListBuilder_Component.builder()
                .parentComponent(dependency)
                .view(view)
                .interactor(interactor)
                .build()
        return component.shopslistRouter()
    }

    override fun inflateView(inflater: LayoutInflater,
                             parentViewGroup: ViewGroup): ShopsListView? {
        return inflater.inflate(R.layout.shoplist_rib, parentViewGroup,
                false) as ShopsListView
    }

    interface ParentComponent {
        fun shopsRepository(): ShopsRepository
    }

    @dagger.Module
    abstract class Module {

        @ShopsListScope
        @Binds
        internal abstract fun presenter(
                view: ShopsListView): ShopsListInteractor.ShopsListPresenter

        @dagger.Module
        companion object {

            @ShopsListScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    view: ShopsListView,
                    interactor: ShopsListInteractor): ShopsListRouter {
                return ShopsListRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @ShopsListScope
    @dagger.Component(modules = [Module::class],
            dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<ShopsListInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {

            @BindsInstance
            fun interactor(interactor: ShopsListInteractor): Builder

            @BindsInstance
            fun view(view: ShopsListView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun shopslistRouter(): ShopsListRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class ShopsListScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class ShopsListInternal
}
