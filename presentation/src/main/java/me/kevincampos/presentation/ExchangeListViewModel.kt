package me.kevincampos.presentation

import android.arch.lifecycle.ViewModel
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.kevincampos.domain.GetExchangesUseCase
import javax.inject.Inject

class ExchangeListViewModel @Inject constructor(
    private val getExchanges: GetExchangesUseCase
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun use() {
        Log.e("TAG", "Using ExchangeListViewModel")

        uiScope.launch(Dispatchers.IO) {
            val exchanges = getExchanges()
            println(exchanges)
        }
    }

}