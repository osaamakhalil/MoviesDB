package com.example.moviesdb.presentation.moviedetails.viewstate

sealed class MovieDetailsViewAction {

    data class GetMovieDetails(val id: Long): MovieDetailsViewAction()
}