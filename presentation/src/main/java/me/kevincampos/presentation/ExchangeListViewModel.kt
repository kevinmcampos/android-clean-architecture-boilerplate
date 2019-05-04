package me.kevincampos.presentation

import android.util.Log
import me.kevincampos.domain.GetExchangesUseCase

class ExchangeListViewModel(
    private val getExchangesUseCase: GetExchangesUseCase
) {

    fun use() {
        Log.e("TAG", "Using ExchangeListViewModel")
        getExchangesUseCase.use()
    }

}