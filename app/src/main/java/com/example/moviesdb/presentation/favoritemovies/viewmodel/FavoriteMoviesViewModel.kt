package com.example.moviesdb.presentation.favoritemovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.usecase.GetMoviesLocalUseCase
import com.example.moviesdb.domain.usecase.RemoveMovieUseCase
import com.example.moviesdb.presentation.favoritemovies.viewstate.FavoriteMoviesViewAction
import com.example.moviesdb.presentation.favoritemovies.viewstate.FavoriteMoviesViewState
import com.example.moviesdb.presentation.movies.mapper.MovieUiMapper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val removeMovieUseCase: RemoveMovieUseCase,
    private val getMoviesLocalUseCase: GetMoviesLocalUseCase,
    private val movieUiMapper: MovieUiMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = MutableLiveData<FavoriteMoviesViewState>()
    val viewState: LiveData<FavoriteMoviesViewState>
        get() = _viewState

    private var movies = mutableListOf<Movie>()
    private var localMoviesIDs = mutableListOf<Long>()

    fun postAction(action: FavoriteMoviesViewAction) {
        when (action) {
            FavoriteMoviesViewAction.GetMovies -> getMovies()
            is FavoriteMoviesViewAction.RemoveFromFavorites -> removeMovieFromFavorites(id = action.id)
        }
    }

    private fun getMovies() {
        getMoviesLocalUseCase.execute().subscribe({ moviesList ->
            movies.clear()
            localMoviesIDs.clear()
            movies.addAll(moviesList)
            localMoviesIDs = moviesList.map { it.id }.toMutableList()
            _viewState.postValue(
                FavoriteMoviesViewState(
                    movies = movies.map { movieUiMapper.map(it, localMoviesIDs) }
                )
            )
        }, { exception ->
            Timber.w(exception)
        })
    }

    private fun removeMovieFromFavorites(id: Long) {
        removeMovieUseCase.execute(movie = movies.find { it.id == id }!!).subscribe({
            getMovies()
        }, { exception ->
            Timber.w(exception)
        })
    }
}