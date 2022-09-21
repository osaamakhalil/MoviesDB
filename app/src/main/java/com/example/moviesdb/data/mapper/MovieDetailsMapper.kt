package com.example.moviesdb.data.mapper

import com.example.moviesdb.data.model.MovieDetailsEntity
import com.example.moviesdb.domain.model.MovieDetails
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {

    fun map(input: MovieDetailsEntity): MovieDetails {

        return MovieDetails(
            id = input.id,
            title = input.title,
            backImage = input.backImage,
            overview = input.overview,
            releaseDate = input.releaseDate,
            revenue = input.revenue,
            status = input.status,
            tagline = input.tagline,
            poster = input.poster
        )
    }
}