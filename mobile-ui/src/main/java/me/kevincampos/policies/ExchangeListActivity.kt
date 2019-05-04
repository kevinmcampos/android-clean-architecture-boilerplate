package me.kevincampos.policies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import me.kevincampos.cache.ExchangeCacheImpl
import me.kevincampos.data.ExchangeRepositoryImpl
import me.kevincampos.domain.GetExchangesUseCase
import me.kevincampos.presentation.ExchangeListViewModel
import me.kevincampos.remote.ExchangeRemoteImpl
import me.kevincampos.remote.service.ExchangeServiceFactory

class ExchangeListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("TAG", "Using ExchangeListActivity")

        val exchangeService = ExchangeServiceFactory.makeCurrencyService(true)

        val getExchangesUseCase = GetExchangesUseCase(ExchangeRepositoryImpl(ExchangeCacheImpl(), ExchangeRemoteImpl(exchangeService)))
        ExchangeListViewModel(getExchangesUseCase).use()
    }

}