package me.kevincampos.domain.model

class Exchange(
    val id: String,
    val name: String,
    val year: Int?,
    val country: String?,
    val description: String,
    val url: String,
    val imageUrl: String,
    val hasTradingIncentive: Boolean?,
    val btcTradeVolumeInLast24Hours: Double
)