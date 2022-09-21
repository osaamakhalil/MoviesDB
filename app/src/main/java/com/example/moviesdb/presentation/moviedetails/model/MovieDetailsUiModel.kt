package com.example.moviesdb.presentation.moviedetails.model

data class MovieDetailsUiModel(
    val id: Long,
    val title: String,
    val backImage: String,
    val overview: String,
    val releaseDate: String,
    val status: String,
    val tagline: String
)