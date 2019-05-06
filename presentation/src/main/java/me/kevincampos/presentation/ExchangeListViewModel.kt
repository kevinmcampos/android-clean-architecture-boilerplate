package me.kevincampos.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.kevincampos.domain.interactor.GetExchangesUseCase
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import me.kevincampos.presentation.state.Resource
import me.kevincampos.presentation.state.ResourceState
import javax.inject.Inject

class ExchangeListViewModel @Inject constructor(
    private val getExchanges: GetExchangesUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<Exchange>>> = MutableLiveData()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            liveData.postValue(Resource(ResourceState.LOADING, null, null))
            refreshExchanges()
        }
    }

    fun getExchangesObservable(): MutableLiveData<Resource<List<Exchange>>> {
        return liveData
    }

    private suspend fun refreshExchanges() {
        val result = getExchanges()
        if (result is Result.Success) {
            liveData.postValue(Resource(ResourceState.SUCCESS, result.data, null))
        } else {
            liveData.postValue(Resource(ResourceState.ERROR, null, "Failed to refresh exchanges"))
        }
    }

}