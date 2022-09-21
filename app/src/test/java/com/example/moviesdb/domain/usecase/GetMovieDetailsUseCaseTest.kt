package com.example.moviesdb.domain.usecase

import com.example.moviesdb.domain.model.MovieDetails
import com.example.moviesdb.domain.repository.Repository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {

    private val repository = mock<Repository>()
    private val useCase = GetMovieDetailsUseCase(
        repository = repository
    )

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }

    @Test
    fun`use case calls repository with the passed id`() {
        stubGetMovieDetails(Single.never())
        val id = 10L

        useCase.execute(id)

        verify(repository).getMovieDetails(id)
    }

    @Test
    fun`use case returns error when something goes wrong`() {
        val error = Throwable()
        val id = 18L
        stubGetMovieDetails(Single.error(error))

        val testObserver = useCase.execute(id).test()

        testObserver.assertError(error)
    }

    @Test
    fun`use case return the result if all goes well`() {
        val movieDetails = MovieDetails(
            id = 20L,
            title = "title",
            backImage = "backImage",
            overview = "overview",
            releaseDate = "releaseDate",
            revenue = 200,
            status = "status",
            tagline = "tagline",
            poster = "poster"
        )
        val id = 20L
        stubGetMovieDetails(Single.just(movieDetails))

        val testObserver = useCase.execute(id).test()

        testObserver.assertValue(movieDetails)
    }

    private fun stubGetMovieDetails(single: Single<MovieDetails>) {
        whenever(repository.getMovieDetails(any()))
            .thenReturn(single)
    }
}