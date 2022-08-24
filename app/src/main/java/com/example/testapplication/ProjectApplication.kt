package com.example.testapplication

import android.app.Application
import android.content.Context
import com.example.testapplication.di.AppComponent
import com.example.testapplication.di.DaggerAppComponent

class ProjectApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .apiKey(BuildConfig.NEWS_API_KEY)
            .application(this)
            .build()
    }
}
