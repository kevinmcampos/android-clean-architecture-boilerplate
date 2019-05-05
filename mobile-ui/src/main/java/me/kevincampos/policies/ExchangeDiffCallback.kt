package me.kevincampos.policies

import android.support.v7.util.DiffUtil
import me.kevincampos.domain.model.Exchange

class ExchangeDiffCallback(
    private val oldList: List<Exchange>,
    private val newList: List<Exchange>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldListPosition: Int, newListPosition: Int): Boolean {
        return oldList[oldListPosition].id == newList[newListPosition].id
    }

    override fun areContentsTheSame(oldListPosition: Int, newListPosition: Int): Boolean {
        return oldList[oldListPosition].btcTradeVolumeInLast24Hours == newList[newListPosition].btcTradeVolumeInLast24Hours
    }

}
