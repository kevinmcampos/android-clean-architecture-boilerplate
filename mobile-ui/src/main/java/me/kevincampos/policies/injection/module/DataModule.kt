package me.kevincampos.policies.injection.module

import dagger.Binds
import dagger.Module
import me.kevincampos.data.ExchangeRepositoryImpl
import me.kevincampos.domain.ExchangeRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindExchangeRepository(repository: ExchangeRepositoryImpl): ExchangeRepository

}