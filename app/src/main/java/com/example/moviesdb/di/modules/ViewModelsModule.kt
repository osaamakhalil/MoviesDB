package com.example.moviesdb.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.moviesdb.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?

}