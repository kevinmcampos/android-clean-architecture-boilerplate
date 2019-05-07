package me.kevincampos.domain.interactor

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
import me.kevincampos.domain.ExchangeRepository
import me.kevincampos.domain.interactor.factory.ExchangeFactory
import me.kevincampos.domain.util.Result
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class GetExchangesUseCaseTest {

    private val exchangeRepository: ExchangeRepository = mock()
    private val getExchangesUseCase: GetExchangesUseCase = GetExchangesUseCase(exchangeRepository)

    @Test
    fun getExchangesReturnsSuccess() {
        runBlocking {
            val fakeSuccessResult = Result.Success(
                listOf(
                    ExchangeFactory.makeExchange(),
                    ExchangeFactory.makeExchange()
                )
            )

            whenever(exchangeRepository.getExchanges(cacheOnly = false))
                .thenReturn(fakeSuccessResult)

            val result = getExchangesUseCase()
//            verify(exchangeRepository).getExchanges()
            assert(result is Result.Success)
            assertEquals(fakeSuccessResult, result)
        }
    }

    @Test
    fun getExchangesReturnsError() {
        runBlocking {
            whenever(exchangeRepository.getExchanges(cacheOnly = false))
                .thenReturn(Result.Error(IOException("Failed to get exchanges from remote")))

            val result = getExchangesUseCase()

            verify(exchangeRepository).getExchanges(cacheOnly = false)

            assert(result is Result.Error && result.exception is IOException)
        }
    }

}