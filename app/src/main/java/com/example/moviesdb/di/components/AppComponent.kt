package com.example.moviesdb.di.components

import android.content.Context
import com.example.moviesdb.MainActivity
import com.example.moviesdb.MyApplication
import com.example.moviesdb.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteModule::class,
        LocalModule::class,
        ViewModelsModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun inject(app: MyApplication)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Context): AppComponent

    }
}