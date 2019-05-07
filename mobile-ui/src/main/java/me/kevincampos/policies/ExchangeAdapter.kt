package me.kevincampos.policies

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.kevincampos.domain.model.Exchange
import me.kevincampos.policies.databinding.ItemExchangeBinding


class ExchangeAdapter(
        private val onItemSelected: (Exchange) -> Unit
) : RecyclerView.Adapter<ExchangeAdapter.ExchangeViewHolder>() {

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
        val inflater = LayoutInflater.from(parentView.context)
        val binding =
                DataBindingUtil.inflate<ItemExchangeBinding>(inflater, R.layout.item_exchange, parentView, false)

        return ExchangeViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ExchangeViewHolder, position: Int) {
        viewHolder.onBind(exchanges[position], onItemSelected)
    }

    class ExchangeViewHolder(private val binding: ItemExchangeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(
                exchange: Exchange,
                onItemSelected: (Exchange) -> Unit
        ) {
            binding.exchange = exchange

            itemView.setOnClickListener {
                onItemSelected(exchange)
            }
        }
    }

}
