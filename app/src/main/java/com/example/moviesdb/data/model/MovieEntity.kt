package com.example.moviesdb.data.model

data class MovieEntity(
    val id: Long,
    val title: String,
    val poster: String,
    val releaseDate: String,
    val overview: String
)