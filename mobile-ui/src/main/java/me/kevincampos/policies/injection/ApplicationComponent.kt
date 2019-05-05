package me.kevincampos.policies.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import me.kevincampos.policies.ExchangeListApplication
import me.kevincampos.policies.injection.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        PresentationModule::class,
        DataModule::class,
        CacheModule::class,
        RemoteModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: ExchangeListApplication)

}