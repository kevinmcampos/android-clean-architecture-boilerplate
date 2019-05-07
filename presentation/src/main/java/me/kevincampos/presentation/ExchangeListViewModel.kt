package me.kevincampos.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.StringRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.kevincampos.domain.interactor.GetExchangesUseCase
import me.kevincampos.domain.model.Exchange
import me.kevincampos.domain.util.Event
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
            val result = getExchanges(cacheOnly = true)
            if (result is Result.Success) {
                _uiState.postValue(ExchangeListUiState.successWithLoading(result.data))
            } else {
                _uiState.postValue(ExchangeListUiState.loading())
            }

            refreshExchanges()
        }
    }

    private suspend fun refreshExchanges() {
        val result = getExchanges()
        if (result is Result.Success) {
            _uiState.postValue(ExchangeListUiState.success(result.data))
        } else {
            _uiState.postValue(ExchangeListUiState.error(R.string.failed))
        }
    }

}

class ExchangeListUiState private constructor(
    val showLoading: Boolean,
    val exchanges: List<Exchange>?,
    val errorStringRes: Event<Int>?
) {

    companion object {
        fun success(data: List<Exchange>): ExchangeListUiState {
            return ExchangeListUiState(false, data, null)
        }

        fun successWithLoading(data: List<Exchange>): ExchangeListUiState {
            return ExchangeListUiState(true, data, null)
        }

        fun error(@StringRes errorStringRes: Int): ExchangeListUiState {
            return ExchangeListUiState(false, null, Event(errorStringRes))
        }

        fun loading(): ExchangeListUiState {
            return ExchangeListUiState(true, null, null)
        }
    }

    enum class State {
        LOADING, SUCCESS, ERROR
    }

}
