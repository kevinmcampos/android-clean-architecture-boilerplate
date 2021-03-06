package me.kevincampos.policies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
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

            uiState.showError?.apply {
                consume()?.let { stringError -> displayError(stringError) }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.refresh_action) {
            viewModel.refresh()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        binding.exchangeList.adapter = ExchangeAdapter { exchange ->
            // TODO: Open exchange url on the browser
        }
    }

    private fun displayError(@StringRes errorString: Int) {
        Snackbar.make(binding.root, errorString, Snackbar.LENGTH_LONG)
            .setAction(R.string.dismiss) { /* do nothing */ }.apply {
                val params = view.layoutParams as FrameLayout.LayoutParams
                val marginInDps =
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics).toInt()

                params.setMargins(
                    params.leftMargin + marginInDps,
                    params.topMargin,
                    params.rightMargin + marginInDps,
                    params.bottomMargin + marginInDps
                )

                view.layoutParams = params
                show()
            }
    }

}
