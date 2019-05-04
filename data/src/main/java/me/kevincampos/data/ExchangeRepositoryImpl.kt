package me.kevincampos.data

import me.kevincampos.data.cache.ExchangeCache
import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.domain.ExchangeRepository
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val exchangeCache: ExchangeCache,
    private val exchangeRemote: ExchangeRemote
) : ExchangeRepository {

    override suspend fun getExchanges(): Result<List<Exchange>> {
        val getExchangesRemoteResult = exchangeRemote.getExchanges()
        if (getExchangesRemoteResult is Result.Success) {
            exchangeCache.insertExchanges(getExchangesRemoteResult.data)
        }
        return exchangeCache.getExchanges()
    }

}