package com.example.moviesdb.presentation.movies.model

data class MovieUiModel(
    val id: Long,
    val title: String,
    val poster: String,
    val releaseDate: String,
    val overview: String,
    val isLiked: Boolean
)