package com.example.moviesdb.data.model

data class MovieDetailsEntity(
    val id: Long,
    val title: String,
    val backImage: String,
    val overview: String,
    val releaseDate: String,
    val revenue: Int,
    val status: String,
    val tagline: String
)