package me.kevincampos.policies

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.kevincampos.domain.model.Exchange


class ExchangeAdapter(
    private val onItemSelected: (Exchange) -> Unit
) : RecyclerView.Adapter<ExchangeViewHolder>() {

    private var exchanges = arrayListOf<Exchange>()

    fun swap(newExchanges: List<Exchange>) {
        val calculateDiff = DiffUtil.calculateDiff(ExchangeDiffCallback(exchanges.toList(), newExchanges))
        calculateDiff.dispatchUpdatesTo(this)

        this.exchanges.clear()
        this.exchanges.addAll(newExchanges)
    }

    override fun getItemCount(): Int {
        return exchanges.size
    }

    override fun onCreateViewHolder(parentView: ViewGroup, position: Int): ExchangeViewHolder {
        val itemView = LayoutInflater.from(parentView.context)
            .inflate(R.layout.item_exchange, parentView, false)
        return ExchangeViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ExchangeViewHolder, position: Int) {
        viewHolder.onBind(exchanges[position], onItemSelected)
    }

}
