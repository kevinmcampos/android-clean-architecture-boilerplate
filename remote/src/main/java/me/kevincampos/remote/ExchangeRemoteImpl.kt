package me.kevincampos.remote

import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import me.kevincampos.domain.util.safeApiCall
import me.kevincampos.remote.mapper.ExchangeResponseMapper
import me.kevincampos.remote.service.ExchangeService
import java.io.IOException
import javax.inject.Inject

class ExchangeRemoteImpl @Inject constructor(
    private val service: ExchangeService,
    private val mapper: ExchangeResponseMapper
) : ExchangeRemote {

    override suspend fun fetchExchanges() = safeApiCall(
        call = { requestGetExchanges() },
        errorMessage = "Unable to get exchanges from remote"
    )

    private suspend fun requestGetExchanges(): Result<List<Exchange>> {
        val response = service.fetchExchangesAsync().await()

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