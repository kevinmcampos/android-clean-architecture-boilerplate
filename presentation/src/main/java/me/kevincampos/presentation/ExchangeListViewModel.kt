package me.kevincampos.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.kevincampos.domain.interactor.GetExchangesUseCase
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Result
import javax.inject.Inject

class ExchangeListViewModel @Inject constructor(
    private val getExchanges: GetExchangesUseCase,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableLiveData<ExchangeListUiState>()
    val uiState: LiveData<ExchangeListUiState>
        get() = _uiState

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.postValue(ExchangeListUiState.loading())
            refreshExchanges()
        }
    }

    private suspend fun refreshExchanges() {
        val result = getExchanges()
        if (result is Result.Success) {
            _uiState.postValue(ExchangeListUiState.success(result.data))
        } else {
            _uiState.postValue(ExchangeListUiState.error("Failed to refresh exchanges"))
        }
    }

}

class ExchangeListUiState private constructor(
    private val state: State,
    val exchanges: List<Exchange>?,
    val errorMessage: String?
) {

    companion object {
        fun success(data: List<Exchange>): ExchangeListUiState {
            return ExchangeListUiState(State.SUCCESS, data, null)
        }

        fun error(errorMessage: String?): ExchangeListUiState {
            return ExchangeListUiState(State.ERROR, null, errorMessage)
        }

        fun loading(): ExchangeListUiState {
            return ExchangeListUiState(State.LOADING, null, null)
        }
    }

    enum class State {
        LOADING, SUCCESS, ERROR
    }

    fun isLoading() = state == State.LOADING
    fun isSuccess() = state == State.SUCCESS
    fun isError() = state == State.ERROR

}
