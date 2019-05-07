package me.kevincampos.data

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.kevincampos.data.cache.ExchangeCache
import me.kevincampos.data.factory.ExchangeFactory
import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.domain.util.Result
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ExchangeRepositoryImplTest {

    private val exchangeCache: ExchangeCache = mock()
    private val exchangeRemote: ExchangeRemote = mock()

    private val exchangeRepository = ExchangeRepositoryImpl(exchangeCache, exchangeRemote)

    @Test
    fun getExchangesFetchRemoteSuccessfully() {
        runBlocking {
            val fakeResult = Result.Success(
                listOf(
                    ExchangeFactory.makeExchange(),
                    ExchangeFactory.makeExchange()
                )
            )

            whenever(exchangeRemote.fetchExchanges())
                .thenReturn(async { fakeResult }.await())
            whenever(exchangeCache.getExchanges())
                .thenReturn(async { fakeResult }.await())

            val result = exchangeRepository.getExchanges(cacheOnly = true)
//            verify(exchangeRemote).fetchExchanges()
//            verify(exchangeCache).insertExchanges(fakeResult.data)

            assert(result is Result.Success)
            assertEquals(fakeResult, result)
        }
    }

    @Test
    fun getExchangesFetchRemoteFails() {

        runBlocking {
            whenever(exchangeRemote.fetchExchanges())
                .thenReturn(Result.Error(IOException("Failed to get exchanges from remote")))

            val fakeCacheResult = Result.Success(
                listOf(
                    ExchangeFactory.makeExchange(),
                    ExchangeFactory.makeExchange()
                )
            )

            whenever(exchangeCache.getExchanges())
                .thenReturn(fakeCacheResult)

            val result =
                exchangeRepository.getExchanges(cacheOnly = true)
//            verify(exchangeRemote).fetchExchanges()
//            verify(exchangeCache, never()).insertExchanges(any())

            assert(result is Result.Success)
            assertEquals(fakeCacheResult, result)
        }
    }

}
