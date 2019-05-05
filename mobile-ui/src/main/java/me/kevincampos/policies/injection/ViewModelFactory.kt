package me.kevincampos.policies.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

class ViewModelFactory<VM : ViewModel> @Inject constructor(
    private val viewModel: Lazy<VM>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}