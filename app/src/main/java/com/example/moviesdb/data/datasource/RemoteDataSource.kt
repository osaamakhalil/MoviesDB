package com.example.moviesdb.data.datasource

import com.example.moviesdb.data.model.MovieDetailsEntity
import com.example.moviesdb.data.model.MovieResultEntity
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {

    fun getMovies(page: Int): Single<MovieResultEntity>

    fun getMoviesDetails(id: Long): Single<MovieDetailsEntity>

}