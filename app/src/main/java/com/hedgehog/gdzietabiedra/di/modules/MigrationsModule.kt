package com.hedgehog.gdzietabiedra.di.modules

import com.hedgehog.gdzietabiedra.data.migration.Version0Migration
import com.hedgehog.gdzietabiedra.data.migration.VersionMigration
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
class MigrationsModule {

  @Provides
  @IntoMap
  @IntKey(0)
  fun provideVersion1Migration(): VersionMigration {
    return Version0Migration()
  }
}