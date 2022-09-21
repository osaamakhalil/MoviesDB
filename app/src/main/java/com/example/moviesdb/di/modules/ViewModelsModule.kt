package com.example.moviesdb.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdb.di.ViewModelKey
import com.example.moviesdb.di.ViewModelProviderFactory
import com.example.moviesdb.presentation.favoritemovies.viewmodel.FavoriteMoviesViewModel
import com.example.moviesdb.presentation.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.moviesdb.presentation.movies.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(viewModel: MovieDetailsViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMoviesViewModel::class)
    abstract fun bindFavoriteMoviesViewModel(viewModel: FavoriteMoviesViewModel?): ViewModel?

}