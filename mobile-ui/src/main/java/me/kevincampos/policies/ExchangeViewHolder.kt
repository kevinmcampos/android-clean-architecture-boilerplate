package me.kevincampos.policies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import me.kevincampos.domain.model.Exchange

class ExchangeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val exchangeIcon: ImageView = itemView.findViewById(R.id.exchange_icon)
    private val exchangeCountry: TextView = itemView.findViewById(R.id.exchange_country)
    private val exchangeName: TextView = itemView.findViewById(R.id.exchange_name)
    private val exchangeBtcVolume: TextView = itemView.findViewById(R.id.exchange_btc_volume)

    fun onBind(
        exchange: Exchange,
        onItemSelected: (Exchange) -> Unit
    ) {
        itemView.setOnClickListener {
            onItemSelected(exchange)
        }

        Glide.with(itemView.context)
            .load(exchange.imageUrl)
            .into(exchangeIcon)

        exchangeName.text = exchange.name
        if (exchange.country == null) {
            exchangeCountry.visibility = View.GONE
        } else {
            exchangeCountry.visibility = View.VISIBLE
            exchangeCountry.text = exchange.country
        }
        exchangeBtcVolume.text = "%.2f BTC".format(exchange.btcTradeVolumeInLast24Hours)
    }
}
