package me.kevincampos.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.StringRes
import kotlinx.coroutines.*
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
            loadExchangesFromCache()
        }
    }

    private suspend fun loadExchangesFromCache() {
        val result = getExchanges(cacheOnly = true)

        withContext(dispatcherProvider.main) {
            if (result is Result.Success) {
                _uiState.value = ExchangeListUiState.successWithLoading(result.data)
            } else {
                _uiState.value = ExchangeListUiState.loading()
            }
        }

        fetchExchangesFromRemote()
    }

    private suspend fun fetchExchangesFromRemote() {
        val result = getExchanges()

        withContext(dispatcherProvider.main) {
            if (result is Result.Success) {
                _uiState.value = ExchangeListUiState.success(result.data)
            } else {
                _uiState.value = _uiState.value?.withError(R.string.failed)
            }
        }
    }

    fun refresh() {
        _uiState.value = _uiState.value?.withLoading()

        viewModelScope.launch(dispatcherProvider.io) {
            fetchExchangesFromRemote()
        }
    }

}

class ExchangeListUiState private constructor(
    val isLoading: Boolean,
    val exchanges: List<Exchange>?,
    val showError: Event<Int>?
) {

    companion object {
        fun success(data: List<Exchange>): ExchangeListUiState {
            return ExchangeListUiState(false, data, null)
        }

        fun successWithLoading(data: List<Exchange>): ExchangeListUiState {
            return ExchangeListUiState(true, data, null)
        }

        fun loading(): ExchangeListUiState {
            return ExchangeListUiState(true, null, null)
        }
    }

    fun withLoading(): ExchangeListUiState {
        return ExchangeListUiState(true, exchanges, null)
    }

    fun withError(@StringRes errorStringRes: Int): ExchangeListUiState {
        return ExchangeListUiState(false, exchanges, Event(errorStringRes))
    }

}
