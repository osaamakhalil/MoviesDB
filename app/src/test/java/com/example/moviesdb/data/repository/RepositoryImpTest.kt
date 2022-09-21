package com.example.moviesdb.data.repository

import com.example.moviesdb.data.datasource.LocalDataSource
import com.example.moviesdb.data.datasource.RemoteDataSource
import com.example.moviesdb.data.mapper.MovieDetailsMapper
import com.example.moviesdb.data.mapper.MovieMapper
import com.example.moviesdb.data.model.MovieDetailsEntity
import com.example.moviesdb.data.model.MovieEntity
import com.example.moviesdb.data.model.MovieResultEntity
import com.example.moviesdb.domain.model.Movie
import com.example.moviesdb.domain.model.MovieDetails
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class RepositoryImpTest {

    private val remoteDataSource = mock<RemoteDataSource>()
    private val localDataSource = mock<LocalDataSource>()
    private val movieMapper = mock<MovieMapper>()
    private val movieDetailsMapper = mock<MovieDetailsMapper>()

    private val repository = RepositoryImp(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        movieMapper = movieMapper,
        movieDetailsMapper = movieDetailsMapper
    )

    @Test
    fun `get movies from remote data source will call remote data source`() {
        val page = 1
        stubGetMovies(Single.never())

        repository.getMovies(page)

        verify(remoteDataSource).getMovies(page)
    }

    @Test
    fun `get movies from remote data source will return result when all goes well`() {
        val page = 1
        val movieResultEntity = MovieResultEntity(
            results = listOf()
        )
        val movies = listOf<Movie>()
        stubGetMovies(Single.just(movieResultEntity))
        stubMapMovie(movies)

        val testObserver = repository.getMovies(page).test()

        testObserver.assertValue(movies)
    }

    @Test
    fun `get movies from local data source will call local data source`() {
        stubGetMoviesFromLocal(Single.never())

        repository.getMoviesFromLocal()

        verify(localDataSource).getMovies()
    }

    @Test
    fun `get movies from local data source will return result when all goes well`() {
        val movieResultEntity = listOf<MovieEntity>()
        val movies = listOf<Movie>()
        stubGetMoviesFromLocal(Single.just(movieResultEntity))
        stubMapMovie(movies)

        val testObserver = repository.getMoviesFromLocal().test()

        testObserver.assertValue(movies)
    }

    @Test
    fun `get movie details from remote data source will call remote data source`() {
        val id = 18L
        stubGetMovieDetails(Single.never())

        repository.getMovieDetails(id)

        verify(remoteDataSource).getMoviesDetails(id)
    }

    @Test
    fun `get movie details from remote data source will return result when all goes well`() {
        val id = 13L
        val movieDetailsEntity = MovieDetailsEntity(
            id = id,
            title = "title",
            backImage = "backImage",
            overview = "overview",
            releaseDate = "releaseDate",
            revenue = 200,
            status = "status",
            tagline = "tagline",
            poster = "poster"
        )
        val movieDetails = MovieDetails(
            id = id,
            title = "title",
            backImage = "backImage",
            overview = "overview",
            releaseDate = "releaseDate",
            revenue = 200,
            status = "status",
            tagline = "tagline",
            poster = "poster"
        )
        stubGetMovieDetails(Single.just(movieDetailsEntity))
        stubMapMovieDetails(movieDetails)

        val testObserver = repository.getMovieDetails(id).test()

        testObserver.assertValue(movieDetails)
    }

    @Test
    fun `insert movie calls local data source`() {
        val movie = Movie(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        val movieEntity = MovieEntity(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        stubInsertMovie(Completable.never())
        stubMapMovie(movieEntity)
        repository.insertMovie(movie)

        verify(localDataSource).insertMovie(movieEntity)
    }

    @Test
    fun `insert movie completes when all goes well`() {
        val movie = Movie(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        val movieEntity = MovieEntity(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        stubInsertMovie(Completable.complete())
        stubMapMovie(movieEntity)
        repository.insertMovie(movie)

        val testObserver = repository.insertMovie(movie).test()

        testObserver.assertComplete()
    }

    @Test
    fun `remove movie calls local data source`() {
        val movie = Movie(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        val movieEntity = MovieEntity(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        stubRemoveMovie(Completable.never())
        stubMapMovie(movieEntity)
        repository.removeMovie(movie)

        verify(localDataSource).removeMovie(movieEntity)
    }

    @Test
    fun `remove movie completes when all goes well`() {
        val movie = Movie(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        val movieEntity = MovieEntity(
            id = 13L,
            title = "title",
            overview = "overview",
            releaseDate = "releaseDate",
            poster = "poster"
        )
        stubRemoveMovie(Completable.complete())
        stubMapMovie(movieEntity)
        repository.removeMovie(movie)

        val testObserver = repository.removeMovie(movie).test()

        testObserver.assertComplete()
    }

    private fun stubGetMovies(single: Single<MovieResultEntity>) {
        whenever(remoteDataSource.getMovies(any()))
            .thenReturn(single)
    }

    private fun stubMapMovie(list: List<Movie>) {
        whenever(movieMapper.mapList(any()))
            .thenReturn(list)
    }

    private fun stubGetMovieDetails(single: Single<MovieDetailsEntity>) {
        whenever(remoteDataSource.getMoviesDetails(any()))
            .thenReturn(single)
    }

    private fun stubMapMovieDetails(movieDetails: MovieDetails) {
        whenever(movieDetailsMapper.map(any()))
            .thenReturn(movieDetails)
    }

    private fun stubGetMoviesFromLocal(single: Single<List<MovieEntity>>) {
        whenever(localDataSource.getMovies())
            .thenReturn(single)
    }

    private fun stubInsertMovie(completable: Completable) {
        whenever(localDataSource.insertMovie(any()))
            .thenReturn(completable)
    }

    private fun stubMapMovie(movieEntity: MovieEntity) {
        whenever(movieMapper.map(any<Movie>()))
            .thenReturn(movieEntity)
    }

    private fun stubRemoveMovie(completable: Completable) {
        whenever(localDataSource.removeMovie(any()))
            .thenReturn(completable)
    }
}