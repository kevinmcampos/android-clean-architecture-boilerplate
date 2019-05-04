package me.kevincampos.remote

import me.kevincampos.data.ExchangeRemote
import me.kevincampos.remote.service.ExchangeService
import javax.inject.Inject

class ExchangeRemoteImpl @Inject constructor(
    private val service: ExchangeService
) : ExchangeRemote {

    override suspend fun getExchanges() {
        val response = service.getExchangesAsync().await()
        println("Using ExchangeRemoteImpl")
    }

}