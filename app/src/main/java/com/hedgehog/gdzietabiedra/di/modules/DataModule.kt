package com.hedgehog.gdzietabiedra.di.modules

import android.content.Context
import asvid.github.io.roomapp.data.migration.Migration
import asvid.github.io.roomapp.data.migration.VersionMigration
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Provider
import javax.inject.Singleton


@Module(includes = [AppModule::class, MigrationsModule::class])
class DataModule {

  @Provides
  @Singleton
  fun provideRealmConfiguration(
      context: Context,
      versionMigrations: Map<Int, @JvmSuppressWildcards Provider<VersionMigration>>): RealmConfiguration {
    Realm.init(context)
    return RealmConfiguration.Builder()
        .schemaVersion(0)
        .migration(Migration(versionMigrations))
        .build()
  }

  @Singleton
  @Provides
  fun shopsRepository(realmConfiguration: RealmConfiguration): ShopsRepository = ShopsRepository(
      realmConfiguration)
}