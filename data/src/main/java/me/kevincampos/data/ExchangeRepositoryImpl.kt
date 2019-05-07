package me.kevincampos.data

import me.kevincampos.data.cache.ExchangeCache
import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.domain.ExchangeRepository
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import java.io.IOException
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val exchangeCache: ExchangeCache,
    private val exchangeRemote: ExchangeRemote
) : ExchangeRepository {

    override suspend fun getExchanges(cacheOnly: Boolean): Result<List<Exchange>> {
        if (cacheOnly) return exchangeCache.getExchanges()

        val remoteResult = exchangeRemote.fetchExchanges()

        if (remoteResult is Result.Success) {
            exchangeCache.insertExchanges(remoteResult.data)
            return exchangeCache.getExchanges()
        } else {
            return Result.Error(IOException("Failed to fetch exchanges and there is no cache"))
        }
    }

}