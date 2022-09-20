package com.example.moviesdb.di.modules

import com.example.moviesdb.data.repository.RepositoryImp
import com.example.moviesdb.domain.repository.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {
    @Binds
    abstract fun bindPostRepository(repository: RepositoryImp): Repository
}