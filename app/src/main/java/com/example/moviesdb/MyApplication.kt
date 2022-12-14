package com.example.moviesdb

import android.app.Application
import com.example.moviesdb.di.components.AppComponent
import com.example.moviesdb.di.components.DaggerAppComponent
import timber.log.Timber


class MyApplication : Application() {

    private val appComponent: AppComponent by lazy {
       DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        provideAppComponent().inject(this)
        Timber.plant(Timber.DebugTree())
    }

    fun provideAppComponent() = appComponent
}