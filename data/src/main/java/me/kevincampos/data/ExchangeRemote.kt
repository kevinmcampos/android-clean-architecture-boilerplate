package me.kevincampos.data

import me.kevincampos.domain.Exchange
import me.kevincampos.domain.Result

interface ExchangeRemote {

    suspend fun getExchanges(): Result<List<Exchange>>

}