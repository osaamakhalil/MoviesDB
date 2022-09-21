package com.example.moviesdb.domain.repository

import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.model.MovieDetails
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getMovies(page: Int): Single<List<Movie>>

    fun getMovieDetails(id: Long): Single<MovieDetails>

    fun getMoviesFromLocal(): Single<List<Movie>>

    fun insertMovie(movie: Movie): Completable

    fun removeMovie(movie: Movie): Completable
}