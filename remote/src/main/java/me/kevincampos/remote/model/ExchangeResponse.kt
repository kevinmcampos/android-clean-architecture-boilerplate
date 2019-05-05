package me.kevincampos.remote.model

import com.google.gson.annotations.SerializedName

class ExchangeResponse(
    val id: String,
    val name: String,
    @SerializedName("year_established") val year: Int?,
    val country: String?,
    val description: String,
    val url: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("has_trading_incentive") val hasTradingIncentive: Boolean?,
    @SerializedName("trade_volume_24h_btc") val btcTradeVolumeInLast24Hours: Double
)
