package com.example.moviesdb.util

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val API_KEY = "d3cce19597af3de623fad70068d7a120"

    const val POPULAR_MOVIES_ENDPOINT = "movie/popular?api_key=$API_KEY"

    const val MOVIE_DETAILS_ENDPOINT = "movie/{movie_id}?api_key=$API_KEY"

    const val SIMILAR_MOVIES_ENDPOINT = "movie/{movie_id}/similar?api_key=$API_KEY"

    const val MOVIES_TABLE_NAME = "movies"

    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    const val PAGINATION_START = 1
}