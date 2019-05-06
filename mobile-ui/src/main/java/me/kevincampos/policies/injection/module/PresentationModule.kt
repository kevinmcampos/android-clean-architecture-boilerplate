package me.kevincampos.policies.injection.module

import dagger.Module
import dagger.Provides
import me.kevincampos.presentation.CoroutinesDispatcherProvider

@Module
abstract class PresentationModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesCoroutines(): CoroutinesDispatcherProvider {
            return CoroutinesDispatcherProvider()
        }
    }
}