package me.kevincampos.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.kevincampos.remote.mapper.ExchangeResponseMapper
import me.kevincampos.remote.model.ExchangeResponse
import me.kevincampos.remote.service.ExchangeService
import org.junit.Test
import retrofit2.Response

class ExchangeRemoteImplTest {

    private val mapper: ExchangeResponseMapper = mock()
    private val exchangeService: ExchangeService = mock()
    private val remote = ExchangeRemoteImpl(exchangeService, mapper)

    @Test
    fun getExchange() {
        runBlocking {
            val exchangeResponse = ExchangeResponse("id", "name", null, null, "AA", "", "", false, 0.1)
            whenever(exchangeService.fetchExchangesAsync())
                .thenReturn(async { Response.success(listOf(exchangeResponse)) })

            val fetchExchanges = remote.fetchExchanges()
            print(fetchExchanges)
        }
    }

}