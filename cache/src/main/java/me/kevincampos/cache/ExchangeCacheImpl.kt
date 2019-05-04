package me.kevincampos.cache

import me.kevincampos.cache.database.ExchangeListDatabase
import me.kevincampos.cache.mapper.ExchangeEntityMapper
import me.kevincampos.data.cache.ExchangeCache
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import javax.inject.Inject

class ExchangeCacheImpl @Inject constructor(
    private val database: ExchangeListDatabase,
    private val mapper: ExchangeEntityMapper
) : ExchangeCache {

    override suspend fun getExchanges(): Result<List<Exchange>> {
        val exchangeEntities = database.exchangeDao().getExchanges()
        val exchanges = exchangeEntities.map { mapper.mapFromEntity(it) }
        return Result.Success(exchanges)
    }

    override suspend fun insertExchanges(exchanges: List<Exchange>): Result<Boolean> {
        database.exchangeDao().insertExchanges(
            exchanges.map { mapper.mapToEntity(it) }
        )
        return Result.Success(true)
    }

}