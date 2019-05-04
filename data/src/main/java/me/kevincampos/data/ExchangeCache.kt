package me.kevincampos.data

import me.kevincampos.domain.Exchange
import me.kevincampos.domain.Result

interface ExchangeCache {

    suspend fun getExchanges(): Result<List<Exchange>>

    suspend fun insertExchanges(exchanges: List<Exchange>): Result<Boolean>

}