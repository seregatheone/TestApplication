package com.example.testapplication.di

import android.app.Application
import com.example.testapplication.data.retrofitrequest.RequestService
import com.example.testapplication.data.retrofitrequest.requestService
import com.example.testapplication.ui.recview.RecyclerViewFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class AppScope

@Scope
annotation class RequestApiKeyQualifier


@[AppScope Component(modules = [AppModule::class])]
interface AppComponent{
    fun inject(recyclerViewFragment: RecyclerViewFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun apiKey(@RequestApiKeyQualifier apiKey:String): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
@Module
class AppModule{
    @Provides
    @AppScope
    fun provideRequestService(@RequestApiKeyQualifier apiKey:String):RequestService = requestService(apiKey)
}

