package me.kevincampos.remote.service

import kotlinx.coroutines.Deferred
import me.kevincampos.remote.model.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeService {

    @GET("exchanges")
    fun fetchExchangesAsync(): Deferred<Response<List<ExchangeResponse>>>

}