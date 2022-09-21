package com.example.moviesdb.presentation.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.usecase.GetMoviesLocalUseCase
import com.example.moviesdb.domain.usecase.GetMoviesUseCase
import com.example.moviesdb.domain.usecase.InsertMovieUseCase
import com.example.moviesdb.domain.usecase.RemoveMovieUseCase
import com.example.moviesdb.presentation.movies.mapper.MovieUiMapper
import com.example.moviesdb.presentation.movies.viewstate.MoviesViewAction
import com.example.moviesdb.presentation.movies.viewstate.MoviesViewState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMoviesLocalUseCase: GetMoviesLocalUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val removeMovieUseCase: RemoveMovieUseCase,
    private val movieUiMapper: MovieUiMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = MutableLiveData<MoviesViewState>()
    val viewState: LiveData<MoviesViewState>
        get() = _viewState

    private var movies = mutableListOf<Movie>()
    private var localMoviesIDs = mutableListOf<Long>()

    init {
        MoviesViewState.Loading.also(_viewState::setValue)
    }

    fun postAction(action: MoviesViewAction) {
        when (action) {
            MoviesViewAction.GetMovies -> getMovies()
            MoviesViewAction.GetMoreMovies -> getMoreMovies()
            is MoviesViewAction.AddToFavorites -> handleClickOnFavorite(id = action.id)
        }
    }

    private fun getMovies() {
        movies.clear()
        _viewState.postValue(MoviesViewState.Loading)
        val localMoviesDisposable = getMoviesLocalUseCase.execute()
        val remoteMoviesDisposable = getMoviesUseCase.execute()

        Single.zip(localMoviesDisposable, remoteMoviesDisposable) { localMovies, remoteMovies ->
            localMoviesIDs = localMovies.map { it.id }.toMutableList()
            movies.addAll(remoteMovies)
            _viewState.postValue(
                MoviesViewState.Success(
                    movies = movies.map { movieUiMapper.map(it, localMoviesIDs) },
                    likesNumber = localMoviesIDs.size.toString()
                )
            )
        }.subscribe({}, { exception ->
            Timber.e(exception)
            _viewState.postValue(MoviesViewState.Error)
        }).also(compositeDisposable::add)
    }

    private fun getMoreMovies() {
        _viewState.postValue((viewState.value as MoviesViewState.Success).copy(loadingMore = true))
        getMoviesUseCase.nextPage().subscribe({ listOfMovies ->
            movies.addAll(listOfMovies)
            _viewState.postValue(
                MoviesViewState.Success(
                    movies = movies.map { movieUiMapper.map(it, localMoviesIDs) },
                    likesNumber = localMoviesIDs.size.toString()
                )
            )
        }, { exception ->
            Timber.e(exception)
        }).also(compositeDisposable::add)
    }

    private fun handleClickOnFavorite(id: Long) {
        if (!localMoviesIDs.contains(id)) {
            insertMovieUseCase.execute(movie = movies.find { it.id == id }!!).subscribe({
                localMoviesIDs.add(id)
                _viewState.postValue(
                    MoviesViewState.Success(
                        movies = movies.map { movieUiMapper.map(it, localMoviesIDs) },
                        likesNumber = localMoviesIDs.size.toString()
                    )
                )
            }, { exception ->
                Timber.e(exception)
            })
        } else {
            removeMovieUseCase.execute(movie = movies.find { it.id == id }!!).subscribe({
                localMoviesIDs.remove(id)
                _viewState.postValue(
                    MoviesViewState.Success(
                        movies = movies.map { movieUiMapper.map(it, localMoviesIDs) },
                        likesNumber = localMoviesIDs.size.toString()
                    )
                )
            }, { exception ->
                Timber.e(exception)
            })
        }
    }
}