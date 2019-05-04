package me.kevincampos.domain.interactor

import me.kevincampos.domain.ExchangeRepository
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import javax.inject.Inject

class GetExchangesUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(): Result<List<Exchange>> {
        return exchangeRepository.getExchanges()
    }

}