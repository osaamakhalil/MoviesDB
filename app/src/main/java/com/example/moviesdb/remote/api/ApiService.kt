package com.example.moviesdb.remote.api

import com.example.moviesdb.remote.model.MovieDetailsRemote
import com.example.moviesdb.remote.model.MovieResultRemote
import com.example.moviesdb.util.Constants.MOVIE_DETAILS_ENDPOINT
import com.example.moviesdb.util.Constants.POPULAR_MOVIES_ENDPOINT
import com.example.moviesdb.util.Constants.SIMILAR_MOVIES_ENDPOINT
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(POPULAR_MOVIES_ENDPOINT)
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResultRemote>

    @GET(MOVIE_DETAILS_ENDPOINT)
    fun getMovieDetails(@Path("movie_id") movieId: Long): Single<MovieDetailsRemote>
}