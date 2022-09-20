package com.example.moviesdb.remote.datasource

import com.example.moviesdb.data.datasource.RemoteDataSource
import com.example.moviesdb.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
}