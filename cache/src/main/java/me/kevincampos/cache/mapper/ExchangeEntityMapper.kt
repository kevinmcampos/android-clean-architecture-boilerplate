package me.kevincampos.cache.mapper

import me.kevincampos.cache.model.ExchangeEntity
import me.kevincampos.domain.model.Exchange
import me.kevincampos.ratesnow.local.mapper.EntityMapper
import javax.inject.Inject

class ExchangeEntityMapper @Inject constructor() : EntityMapper<ExchangeEntity, Exchange> {

    override fun mapFromEntity(entity: ExchangeEntity): Exchange {
        return Exchange(entity.id, entity.name, entity.year, entity.country, entity.description, entity.url,
            entity.imageUrl, entity.hasTradingIncentive, entity.btcTradeVolumeInLast24Hours)
    }

    override fun mapToEntity(domain: Exchange): ExchangeEntity {
        return ExchangeEntity(domain.id, domain.name, domain.year, domain.country, domain.description, domain.url,
            domain.imageUrl, domain.hasTradingIncentive, domain.btcTradeVolumeInLast24Hours)
    }

}