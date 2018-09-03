package com.hedgehog.gdzietabiedra.di

import android.app.Activity
import com.hedgehog.gdzietabiedra.MainActivity
import com.hedgehog.gdzietabiedra.di.components.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class
ActivityBinder {

  @Binds
  @IntoMap
  @ActivityKey(MainActivity::class)
  abstract fun bindMainActivityInjectorFactory(
      builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}