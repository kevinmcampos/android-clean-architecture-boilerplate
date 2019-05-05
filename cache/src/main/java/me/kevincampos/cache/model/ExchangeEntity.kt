package me.kevincampos.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import me.kevincampos.cache.database.ExchangeConstants

@Entity(tableName = ExchangeConstants.TABLE_NAME)
data class ExchangeEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(name = "year_established") val year: Int?,
    val country: String?,
    val description: String,
    val url: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "has_trading_incentive") val hasTradingIncentive: Boolean?,
    @ColumnInfo(name = "btc_trade_volume_in_last_24h_hours") val btcTradeVolumeInLast24Hours: Double
)