package me.kevincampos.data

import me.kevincampos.domain.ExchangeRepository

class ExchangeRepositoryImpl(
    private val exchangeCache: ExchangeCache,
    private val exchangeRemote: ExchangeRemote
) : ExchangeRepository {

    override fun use() {
        println("Using ExchangeRepositoryImpl")

        exchangeCache.use()
        exchangeRemote.use()
    }

}