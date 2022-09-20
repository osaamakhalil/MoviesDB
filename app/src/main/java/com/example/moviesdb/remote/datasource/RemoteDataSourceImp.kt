package com.example.moviesdb.remote.datasource

import com.example.moviesdb.data.datasource.RemoteDataSource
import com.example.moviesdb.data.model.MovieDetailsEntity
import com.example.moviesdb.data.model.MovieResultEntity
import com.example.moviesdb.remote.api.ApiService
import com.example.moviesdb.remote.mapper.MovieDetailsEntityMapper
import com.example.moviesdb.remote.mapper.MovieResultEntityMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val movieResultEntityMapper: MovieResultEntityMapper,
    private val movieDetailsEntityMapper: MovieDetailsEntityMapper
) : RemoteDataSource {

    override fun getMovies(page: Int): Single<MovieResultEntity> {
        return apiService.getPopularMovies(page).map(movieResultEntityMapper::map)
    }

    override fun getMoviesDetails(id: Long): Single<MovieDetailsEntity> {
        return apiService.getMovieDetails(id).map(movieDetailsEntityMapper::map)
    }
}