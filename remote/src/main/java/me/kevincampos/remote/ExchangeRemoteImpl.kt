package me.kevincampos.remote

import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import me.kevincampos.remote.mapper.ExchangeResponseMapper
import me.kevincampos.remote.service.ExchangeService
import java.io.IOException
import javax.inject.Inject

class ExchangeRemoteImpl @Inject constructor(
    private val service: ExchangeService,
    private val mapper: ExchangeResponseMapper
) : ExchangeRemote {

    override suspend fun getExchanges(): Result<List<Exchange>> {
        val response = service.getExchangesAsync().await()

        return if (response.isSuccessful) {
            val exchangeList = response.body()?.map {
                mapper.mapToDomain(it)
            } ?: listOf()

            Result.Success(exchangeList)
        } else {
            Result.Error(IOException("Failed to get exchanges in remote"))
        }
    }

}