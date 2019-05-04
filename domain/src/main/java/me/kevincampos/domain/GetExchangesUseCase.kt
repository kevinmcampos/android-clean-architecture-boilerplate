package me.kevincampos.domain

class GetExchangesUseCase(
    private val policyRepository: ExchangeRepository
) {

    fun use() {
        println("Using GetExchangesUseCase")
        policyRepository.use()
    }

}