package com.example.moviesdb.di.modules

import com.example.moviesdb.data.datasource.LocalDataSource
import com.example.moviesdb.local.MoviesDatabase
import com.example.moviesdb.local.dao.MoviesDAO
import com.example.moviesdb.local.datasource.LocalDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
 abstract class LocalModule {

    companion object {
        @Provides
        @Singleton
        fun getMoviesDao(moviesDatabase: MoviesDatabase): MoviesDAO {
            return moviesDatabase.moviesDao()
        }
    }

    @Binds
    abstract fun bindLocalDataSource(dataSource: LocalDataSourceImp): LocalDataSource
}