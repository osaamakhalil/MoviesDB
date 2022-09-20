package com.example.moviesdb.data.repository

import com.example.moviesdb.data.datasource.LocalDataSource
import com.example.moviesdb.data.datasource.RemoteDataSource
import com.example.moviesdb.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {
}