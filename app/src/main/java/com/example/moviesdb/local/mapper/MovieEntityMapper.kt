package com.example.moviesdb.local.mapper

import com.example.moviesdb.data.model.MovieEntity
import com.example.moviesdb.local.model.MovieLocal
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() {

    fun mapToLocal(input: MovieEntity): MovieLocal {

        return MovieLocal(
            id = input.id,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            poster = input.poster
        )
    }

    fun mapToEntity(input: List<MovieLocal>): List<MovieEntity> {

        return input.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                poster = it.poster
            )
        }
    }
}