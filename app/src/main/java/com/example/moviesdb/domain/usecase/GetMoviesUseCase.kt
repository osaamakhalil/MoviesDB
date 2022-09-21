package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.repository.Repository
import com.example.moviesdb.util.Constants.PAGINATION_START
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: Repository
) {
    private val page = AtomicInteger(PAGINATION_START)

    fun execute(): Single<List<Movie>> {
        page.set(PAGINATION_START)
        val result = repository.getMovies(page.get()).subscribeOn(Schedulers.io())
        page.incrementAndGet()
        return result
    }

    fun nextPage(): Single<List<Movie>> {
        val result = repository.getMovies(page.get()).subscribeOn(Schedulers.io())
        page.incrementAndGet()
        return result
    }
}