package me.kevincampos.data.remote

import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result

interface ExchangeRemote {

    suspend fun fetchExchanges(): Result<List<Exchange>>

}