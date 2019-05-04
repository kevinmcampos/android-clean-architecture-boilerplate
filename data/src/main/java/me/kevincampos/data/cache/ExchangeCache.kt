package me.kevincampos.data.cache

import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result

interface ExchangeCache {

    suspend fun getExchanges(): Result<List<Exchange>>

    suspend fun insertExchanges(exchanges: List<Exchange>): Result<Boolean>

}