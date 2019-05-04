package me.kevincampos.remote.mapper

import me.kevincampos.domain.Exchange
import me.kevincampos.remote.model.ExchangeResponse
import javax.inject.Inject

class ExchangeResponseMapper @Inject constructor():
    ResponseMapper<ExchangeResponse, Exchange> {

    override fun mapToDomain(response: ExchangeResponse): Exchange {
        return Exchange(response.id, response.name)
    }

}