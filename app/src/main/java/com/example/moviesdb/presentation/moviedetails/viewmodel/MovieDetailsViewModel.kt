package com.example.moviesdb.presentation.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.model.MovieDetails
import com.example.moviesdb.domain.usecase.GetMovieDetailsUseCase
import com.example.moviesdb.presentation.moviedetails.mapper.MovieDetailsUiMapper
import com.example.moviesdb.presentation.moviedetails.viewstate.MovieDetailsViewAction
import com.example.moviesdb.presentation.moviedetails.viewstate.MovieDetailsViewState
import com.example.moviesdb.presentation.movies.viewstate.MoviesViewState
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val movieDetailsMapper: MovieDetailsUiMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<MovieDetailsViewState>()
    val viewState: LiveData<MovieDetailsViewState>
        get() = _viewState

    private lateinit var movieDetails: MovieDetails

    init {
        MovieDetailsViewState.Loading.also(_viewState::setValue)
    }

    fun postAction(action: MovieDetailsViewAction) {
        when (action) {
            is MovieDetailsViewAction.GetMovieDetails -> getMovieDetails(id = action.id)
        }
    }

    private fun getMovieDetails(id: Long) {
        _viewState.postValue(MovieDetailsViewState.Loading)
        getMovieDetailsUseCase.execute(id)
            .subscribe({
                movieDetails = it
                _viewState.postValue(
                    MovieDetailsViewState.Success(
                        movie = movieDetailsMapper.map(movieDetails)
                    )
                )
            }, {
                _viewState.postValue(MovieDetailsViewState.Error)
                Timber.e(it)
            })
    }

}