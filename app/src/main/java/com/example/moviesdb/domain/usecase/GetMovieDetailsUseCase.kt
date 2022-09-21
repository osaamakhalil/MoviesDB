package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.model.MovieDetails
import com.example.moviesdb.domain.repository.Repository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(id: Long): Single<MovieDetails> {
        return repository.getMovieDetails(id).subscribeOn(Schedulers.io())
    }
}