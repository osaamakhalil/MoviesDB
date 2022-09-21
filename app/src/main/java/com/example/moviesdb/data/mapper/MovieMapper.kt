package com.example.moviesdb.data.mapper

import com.example.moviesdb.data.model.MovieEntity
import com.example.moviesdb.data.model.MovieResultEntity
import com.example.moviesdb.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun mapList(input: List<MovieEntity>): List<Movie> {

        return input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                poster = it.poster,
                releaseDate = it.releaseDate,
                title = it.title
            )
        }
    }

    fun map(input: MovieEntity): Movie {

        return Movie(
            id = input.id,
            overview = input.overview,
            poster = input.poster,
            releaseDate = input.releaseDate,
            title = input.title
        )
    }

    fun map(input: Movie): MovieEntity {

        return MovieEntity(
            id = input.id,
            overview = input.overview,
            poster = input.poster,
            releaseDate = input.releaseDate,
            title = input.title
        )
    }
}