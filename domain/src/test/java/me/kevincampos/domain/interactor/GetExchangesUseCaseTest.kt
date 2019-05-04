package me.kevincampos.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.runBlocking
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
class GetExchangesUseCaseTest {

    private lateinit var getExchangesUseCase: GetExchangesUseCase
    @Mock
    lateinit var exchangeRepository: ExchangeRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getExchangesUseCase = GetExchangesUseCase(exchangeRepository)
    }

    @Test
    fun getExchangesReturnsSuccess() {
        runBlocking {
            val fakeSuccessResult = Result.Success(
                listOf(
                    Exchange("AA", "AAAA"),
                    Exchange("BB", "BBBB")
                )
            )

            whenever(exchangeRepository.getExchanges())
                .thenReturn(fakeSuccessResult)

            val result = getExchangesUseCase.invoke()
            verify(exchangeRepository).getExchanges()
            assert(result is Result.Success)
            assertEquals(fakeSuccessResult, result)
        }
    }

    @Test
    fun getExchangesReturnsError() {
        runBlocking {
            whenever(exchangeRepository.getExchanges())
                .thenReturn(Result.Error(IOException("Failed to get exchanges from remote")))

            val result = getExchangesUseCase.invoke()

            verify(exchangeRepository).getExchanges()

            assert(result is Result.Error && result.exception is IOException)
        }
    }

}