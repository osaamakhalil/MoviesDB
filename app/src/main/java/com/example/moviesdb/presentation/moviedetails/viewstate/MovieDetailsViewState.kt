package com.example.moviesdb.presentation.moviedetails.viewstate

import com.example.moviesdb.presentation.moviedetails.model.MovieDetailsUiModel

sealed class MovieDetailsViewState {

    object Loading : MovieDetailsViewState()

    data class Success(
        val movie: MovieDetailsUiModel
    ) : MovieDetailsViewState()

    object Error : MovieDetailsViewState()
}