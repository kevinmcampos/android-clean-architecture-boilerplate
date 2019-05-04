package me.kevincampos.cache.mapper

import me.kevincampos.cache.model.ExchangeEntity
import me.kevincampos.domain.Exchange
import me.kevincampos.ratesnow.local.mapper.EntityMapper
import javax.inject.Inject

class ExchangeEntityMapper @Inject constructor() : EntityMapper<ExchangeEntity, Exchange> {

    override fun mapFromEntity(entity: ExchangeEntity): Exchange {
        return Exchange(entity.id, entity.name)
    }

    override fun mapToEntity(domain: Exchange): ExchangeEntity {
        return ExchangeEntity(domain.id, domain.name)
    }

}