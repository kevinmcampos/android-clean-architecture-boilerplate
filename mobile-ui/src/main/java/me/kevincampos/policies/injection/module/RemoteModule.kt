package me.kevincampos.policies.injection.module

import android.support.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.policies.BuildConfig
import me.kevincampos.remote.ExchangeRemoteImpl
import me.kevincampos.remote.service.ExchangeService
import me.kevincampos.remote.service.ExchangeServiceFactory
import okhttp3.OkHttpClient

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideExchangeService(okHttpClient: OkHttpClient): ExchangeService {
            return ExchangeServiceFactory.makeExchangeService(okHttpClient)
        }

        @Provides
        @JvmStatic
        fun provideOkHttpClient(): OkHttpClient {
            val okHttpClient = ExchangeServiceFactory.makeOkHttpClient(BuildConfig.DEBUG)

            if (BuildConfig.DEBUG) {
                IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("OkHttp", okHttpClient))

            }

            return okHttpClient
        }
    }

    @Binds
    abstract fun bindExchangeRemote(exchangeRemote: ExchangeRemoteImpl): ExchangeRemote

}