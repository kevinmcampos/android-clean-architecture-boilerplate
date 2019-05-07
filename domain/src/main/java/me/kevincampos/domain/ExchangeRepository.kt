package me.kevincampos.domain

import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result

interface ExchangeRepository {

    suspend fun getExchanges(cacheOnly: Boolean): Result<List<Exchange>>

}