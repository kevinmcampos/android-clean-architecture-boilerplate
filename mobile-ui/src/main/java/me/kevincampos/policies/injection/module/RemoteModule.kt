package me.kevincampos.policies.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.policies.BuildConfig
import me.kevincampos.remote.ExchangeRemoteImpl
import me.kevincampos.remote.service.ExchangeService
import me.kevincampos.remote.service.ExchangeServiceFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideExchangeService(): ExchangeService {
            return ExchangeServiceFactory.makeExchangeService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindExchangeRemote(exchangeRemote: ExchangeRemoteImpl): ExchangeRemote

}