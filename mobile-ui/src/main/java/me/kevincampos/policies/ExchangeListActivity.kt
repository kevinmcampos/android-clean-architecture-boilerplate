package me.kevincampos.policies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import me.kevincampos.policies.databinding.ActivityExchangeListBinding
import me.kevincampos.policies.injection.ViewModelFactory
import me.kevincampos.presentation.ExchangeListUiState
import me.kevincampos.presentation.ExchangeListViewModel
import javax.inject.Inject

class ExchangeListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewFactory: ViewModelFactory<ExchangeListViewModel>

    private lateinit var binding: ActivityExchangeListBinding
    private lateinit var viewModel: ExchangeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exchange_list)
        viewModel = ViewModelProviders.of(this, viewFactory)[ExchangeListViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        viewModel.uiState.observe(this, Observer<ExchangeListUiState> {
            val uiState = it ?: return@Observer

            uiState.exchanges?.let { exchanges ->
                val adapter = binding.exchangeList.adapter as ExchangeAdapter
                adapter.swap(exchanges)
            }
        })
    }

    private fun initRecyclerView() {
        binding.exchangeList.adapter = ExchangeAdapter { exchange ->
            // TODO: Open exchange url on the browser
        }
    }

}
