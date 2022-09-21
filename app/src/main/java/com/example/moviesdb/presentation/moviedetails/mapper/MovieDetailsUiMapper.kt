package com.example.moviesdb.presentation.moviedetails.mapper

import com.example.moviesdb.domain.model.MovieDetails
import com.example.moviesdb.presentation.moviedetails.model.MovieDetailsUiModel
import com.example.moviesdb.util.Constants.BASE_IMAGE_URL
import javax.inject.Inject

class MovieDetailsUiMapper @Inject constructor() {

    fun map(input: MovieDetails): MovieDetailsUiModel {

        return MovieDetailsUiModel(
            id = input.id,
            title = input.title,
            backImage = BASE_IMAGE_URL + input.backImage,
            overview = input.overview,
            releaseDate = input.releaseDate,
            status = input.status,
            tagline = input.tagline
        )
    }
}