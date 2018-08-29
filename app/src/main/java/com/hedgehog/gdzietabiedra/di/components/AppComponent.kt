package com.hedgehog.gdzietabiedra.di.components

import com.hedgehog.gdzietabiedra.App
import com.hedgehog.gdzietabiedra.di.ActivityBinder
import com.hedgehog.gdzietabiedra.di.modules.AppModule
import com.hedgehog.gdzietabiedra.di.modules.DataModule
import com.hedgehog.gdzietabiedra.di.modules.MigrationsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  ActivityBinder::class,
  AppModule::class,
  MigrationsModule::class,
  DataModule::class])
interface AppComponent {

  fun inject(app: App)

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(app: App): Builder

    fun build(): AppComponent
  }
}