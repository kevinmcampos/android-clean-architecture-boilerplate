package me.kevincampos.domain

import javax.inject.Inject

class GetExchangesUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) {

    suspend operator fun invoke(): Result<List<Exchange>> {
        return exchangeRepository.getExchanges()
    }

}