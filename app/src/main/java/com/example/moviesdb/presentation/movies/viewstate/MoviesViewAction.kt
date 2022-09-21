package com.example.moviesdb.presentation.movies.viewstate

sealed class MoviesViewAction {

    object GetMovies : MoviesViewAction()

    object GetMoreMovies : MoviesViewAction()

    data class AddToFavorites(val id: Long): MoviesViewAction()
}