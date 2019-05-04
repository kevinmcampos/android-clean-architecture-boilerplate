package me.kevincampos.policies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.kevincampos.cache.ExchangeCacheImpl
import me.kevincampos.cache.database.ExchangeListDatabase
import me.kevincampos.cache.mapper.ExchangeEntityMapper
import me.kevincampos.data.ExchangeRepositoryImpl
import me.kevincampos.domain.GetExchangesUseCase
import me.kevincampos.presentation.ExchangeListViewModel
import me.kevincampos.remote.ExchangeRemoteImpl
import me.kevincampos.remote.mapper.ExchangeResponseMapper
import me.kevincampos.remote.service.ExchangeServiceFactory

class ExchangeListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val exchangeService = ExchangeServiceFactory.makeCurrencyService(true)
        val exchangeCache = ExchangeCacheImpl(ExchangeListDatabase.getInstance(baseContext), ExchangeEntityMapper())
        val exchangeRemote = ExchangeRemoteImpl(exchangeService, ExchangeResponseMapper())

        val getExchangesUseCase = GetExchangesUseCase(ExchangeRepositoryImpl(exchangeCache, exchangeRemote))

        ExchangeListViewModel(getExchangesUseCase).use()
    }

}