package me.kevincampos.policies.injection.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.kevincampos.cache.ExchangeCacheImpl
import me.kevincampos.cache.database.ExchangeListDatabase
import me.kevincampos.data.cache.ExchangeCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): ExchangeListDatabase {
            return ExchangeListDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindExchangeCache(exchangeCache: ExchangeCacheImpl): ExchangeCache

}