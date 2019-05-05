package me.kevincampos.remote.mapper

import me.kevincampos.domain.model.Exchange
import me.kevincampos.remote.model.ExchangeResponse
import javax.inject.Inject

class ExchangeResponseMapper @Inject constructor():
    ResponseMapper<ExchangeResponse, Exchange> {

    override fun mapToDomain(response: ExchangeResponse): Exchange {
        return Exchange(response.id, response.name, response.year, response.country, response.description, response.url,
            response.imageUrl, response.hasTradingIncentive, response.btcTradeVolumeInLast24Hours)
    }

}