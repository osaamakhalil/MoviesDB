package com.example.moviesdb.local.datasource

import com.example.moviesdb.data.datasource.LocalDataSource
import com.example.moviesdb.local.dao.MoviesDAO
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val moviesDAO: MoviesDAO
) : LocalDataSource {

}