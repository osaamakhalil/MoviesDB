package com.example.moviesdb.data.datasource

import com.example.moviesdb.data.model.MovieEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface LocalDataSource {

    fun getMovies(): Single<List<MovieEntity>>

    fun insertMovie(movieEntity: MovieEntity): Completable

    fun removeMovie(movieEntity: MovieEntity): Completable
}