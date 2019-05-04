package me.kevincampos.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import me.kevincampos.cache.database.ExchangeConstants

@Entity(tableName = ExchangeConstants.TABLE_NAME)
data class ExchangeEntity(
    @PrimaryKey
    var id: String,
    var name: String
)