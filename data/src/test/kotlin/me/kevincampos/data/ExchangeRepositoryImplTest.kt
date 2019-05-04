package me.kevincampos.data

import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import me.kevincampos.data.cache.ExchangeCache
import me.kevincampos.data.remote.ExchangeRemote
import me.kevincampos.domain.ExchangeRepository
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ExchangeRepositoryImplTest {

    private lateinit var exchangeRepository: ExchangeRepository
    @Mock
    lateinit var exchangeCache: ExchangeCache
    @Mock
    lateinit var exchangeRemote: ExchangeRemote

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        exchangeRepository = ExchangeRepositoryImpl(exchangeCache, exchangeRemote)
    }

    @Test
    fun getExchangesFetchRemoteSuccessfully() {
        runBlocking {
            val fakeResult = me.kevincampos.domain.util.Result.Success(
                listOf(
                    Exchange("AA", "AAAA"),
                    Exchange("BB", "BBBB")
                )
            )

            whenever(exchangeRemote.fetchExchanges())
                .thenReturn(fakeResult)
            whenever(exchangeCache.getExchanges())
                .thenReturn(fakeResult)

            val result = exchangeRepository.getExchanges()
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
                    Exchange("AA", "AAAA"),
                    Exchange("BB", "BBBB")
                )
            )

            whenever(exchangeCache.getExchanges())
                .thenReturn(fakeCacheResult)

            val result =
                exchangeRepository.getExchanges()
//            verify(exchangeRemote).fetchExchanges()
//            verify(exchangeCache, never()).insertExchanges(any())

            assert(result is Result.Success)
            assertEquals(fakeCacheResult, result)
        }
    }


}