package me.kevincampos.domain

interface ExchangeRepository {

    suspend fun getExchanges(): Result<List<Exchange>>

}