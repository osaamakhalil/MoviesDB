package com.example.moviesdb.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val poster: String,
    val releaseDate: String,
    val overview: String
)