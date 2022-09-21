package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RemoveMovieUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(movie: Movie): Completable {
        return repository.removeMovie(movie).subscribeOn(Schedulers.io())
    }
}