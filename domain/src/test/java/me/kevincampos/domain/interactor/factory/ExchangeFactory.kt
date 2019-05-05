package me.kevincampos.domain.interactor.factory

import me.kevincampos.domain.model.Exchange

object ExchangeFactory {

    fun makeExchange(): Exchange {
        return Exchange(
            DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomInt(),
            DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomDouble()
        )
    }

}