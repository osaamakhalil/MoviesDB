package com.example.moviesdb.local.datasource

import com.example.moviesdb.data.datasource.LocalDataSource
import com.example.moviesdb.data.model.MovieEntity
import com.example.moviesdb.local.dao.MoviesDAO
import com.example.moviesdb.local.mapper.MovieEntityMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(
    private val moviesDAO: MoviesDAO,
    private val movieEntityMapper: MovieEntityMapper
) : LocalDataSource {

    override fun getMovies(): Single<List<MovieEntity>> {
        return moviesDAO.getMovie().map(movieEntityMapper::mapToEntity)
    }

    override fun insertMovie(movieEntity: MovieEntity): Completable {
        return moviesDAO.insertMovie(movieEntityMapper.mapToLocal(movieEntity))
    }

    override fun removeMovie(movieEntity: MovieEntity): Completable {
        return moviesDAO.removeMovie(movieEntityMapper.mapToLocal(movieEntity))
    }

}