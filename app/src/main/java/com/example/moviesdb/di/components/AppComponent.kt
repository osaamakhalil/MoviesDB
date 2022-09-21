package com.example.moviesdb.di.components

import android.content.Context
import com.example.moviesdb.MainActivity
import com.example.moviesdb.MyApplication
import com.example.moviesdb.di.modules.*
import com.example.moviesdb.presentation.favoritemovies.view.FavoriteMoviesFragment
import com.example.moviesdb.presentation.moviedetails.view.MovieDetailsFragment
import com.example.moviesdb.presentation.movies.view.MoviesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteModule::class,
        LocalModule::class,
        ViewModelsModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun inject(app: MyApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: MoviesFragment)

    fun inject(fragment: MovieDetailsFragment)

    fun inject(fragment: FavoriteMoviesFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Context): AppComponent

    }
}