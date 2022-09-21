package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.repository.Repository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetMoviesLocalUseCase @Inject constructor(
    private val repository: Repository
) {

    fun execute(): Single<List<Movie>> {
        return repository.getMoviesFromLocal().subscribeOn(Schedulers.io())
    }
}