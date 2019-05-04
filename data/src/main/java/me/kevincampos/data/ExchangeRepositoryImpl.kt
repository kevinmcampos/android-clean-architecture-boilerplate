package me.kevincampos.data

import me.kevincampos.domain.ExchangeRepository
import javax.inject.Inject

class ExchangeRepositoryImpl @Inject constructor(
    private val exchangeCache: ExchangeCache,
    private val exchangeRemote: ExchangeRemote
) : ExchangeRepository {

    override suspend fun use() {
        println("Using ExchangeRepositoryImpl")

        exchangeCache.use()
        exchangeRemote.use()
    }

}