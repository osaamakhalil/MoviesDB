package com.example.moviesdb.presentation.favoritemovies.viewstate

sealed class FavoriteMoviesViewAction {

    object GetMovies : FavoriteMoviesViewAction()

    data class RemoveFromFavorites(val id: Long) : FavoriteMoviesViewAction()
}