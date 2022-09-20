package com.example.moviesdb.di.modules

import com.example.moviesdb.data.datasource.RemoteDataSource
import com.example.moviesdb.remote.api.ApiService
import com.example.moviesdb.remote.datasource.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class RemoteModule {

    companion object {
        @Provides
        @Singleton
         fun provideRedditService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @Binds
    abstract fun bindPostRemoteDataSource(dataSource: RemoteDataSourceImp): RemoteDataSource
}