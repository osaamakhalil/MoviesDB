package com.example.moviesdb.data.repository

import com.example.moviesdb.data.datasource.LocalDataSource
import com.example.moviesdb.data.datasource.RemoteDataSource
import com.example.moviesdb.data.mapper.MovieDetailsMapper
import com.example.moviesdb.data.mapper.MovieMapper
import com.example.moviesdb.data.model.MovieEntity
import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.model.MovieDetails
import com.example.moviesdb.domain.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val movieMapper: MovieMapper,
    private val movieDetailsMapper: MovieDetailsMapper
) : Repository {
    override fun getMovies(page: Int): Single<List<Movie>> {
        return remoteDataSource.getMovies(page).map { movieMapper.mapList(it.results) }
    }

    override fun getMovieDetails(id: Long): Single<MovieDetails> {
        return remoteDataSource.getMoviesDetails(id).map(movieDetailsMapper::map)
    }

    override fun getMoviesFromLocal(): Single<List<Movie>> {
        return localDataSource.getMovies().map(movieMapper::mapList)
    }

    override fun insertMovie(movie: Movie): Completable {
        return localDataSource.insertMovie(movieMapper.map(movie))
    }

    override fun removeMovie(movie: Movie): Completable {
        return localDataSource.removeMovie(movieMapper.map(movie))
    }
}