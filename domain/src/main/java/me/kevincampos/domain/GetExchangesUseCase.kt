package me.kevincampos.domain

import javax.inject.Inject

class GetExchangesUseCase @Inject constructor(
    private val policyRepository: ExchangeRepository
) {

    suspend fun use() {
        println("Using GetExchangesUseCase")
        policyRepository.use()
    }

}