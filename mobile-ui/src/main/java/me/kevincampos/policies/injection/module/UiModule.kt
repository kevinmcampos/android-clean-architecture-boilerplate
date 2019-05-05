package me.kevincampos.policies.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.kevincampos.policies.ExchangeListActivity

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contributesExchangeListActivity(): ExchangeListActivity

}