package me.kevincampos.policies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_exchange_list.*
import me.kevincampos.domain.model.Exchange
import me.kevincampos.policies.injection.ViewModelFactory
import me.kevincampos.presentation.ExchangeListViewModel
import me.kevincampos.presentation.state.Resource
import me.kevincampos.presentation.state.ResourceState
import javax.inject.Inject

class ExchangeListActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory<ExchangeListViewModel>

    private lateinit var viewModel: ExchangeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_list)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, vmFactory)[ExchangeListViewModel::class.java]

        exchange_list.layoutManager = LinearLayoutManager(this)
        exchange_list.adapter = ExchangeAdapter { exchange ->
            // TODO: Open exchange url on the browser
        }

        viewModel.getExchangesObservable().observe(this,
            Observer<Resource<List<Exchange>>> {
                it?.let {
                    handleDataState(it)
                }
            })
    }

    private fun handleDataState(resource: Resource<List<Exchange>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data)
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                exchange_list.visibility = View.GONE
                error_message.visibility = View.GONE
            }
            ResourceState.ERROR -> {
                progress.visibility = View.GONE
                exchange_list.visibility = View.GONE
                error_message.visibility = View.VISIBLE
                error_message.text = resource.message
            }
        }
    }

    private fun setupScreenForSuccess(exchanges: List<Exchange>?) {
        progress.visibility = View.GONE
        error_message.visibility = View.GONE
        exchanges?.let {
            val adapter = exchange_list.adapter as ExchangeAdapter
            adapter.swap(it)
            exchange_list.visibility = View.VISIBLE
        }
    }

}