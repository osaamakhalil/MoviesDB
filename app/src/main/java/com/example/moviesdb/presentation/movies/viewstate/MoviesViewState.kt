package com.example.moviesdb.presentation.movies.viewstate

import com.example.moviesdb.presentation.movies.model.MovieUiModel

sealed class MoviesViewState {

    object Loading : MoviesViewState()

    data class Success(
        val movies: List<MovieUiModel>,
        val loadingMore: Boolean = false,
        val likesNumber: String
    ) : MoviesViewState()

    object Error : MoviesViewState()
}