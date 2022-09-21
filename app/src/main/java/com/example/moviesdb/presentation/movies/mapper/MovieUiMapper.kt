package com.example.moviesdb.presentation.movies.mapper

import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.presentation.movies.model.MovieUiModel
import com.example.moviesdb.util.Constants.BASE_IMAGE_URL
import javax.inject.Inject

class MovieUiMapper @Inject constructor() {

    fun map(input: Movie, ids: List<Long>): MovieUiModel {

        return MovieUiModel(
            id = input.id,
            overview = input.overview,
            poster = BASE_IMAGE_URL + input.poster,
            releaseDate = input.releaseDate,
            title = input.title,
            isLiked = ids.contains(input.id)
        )
    }
}