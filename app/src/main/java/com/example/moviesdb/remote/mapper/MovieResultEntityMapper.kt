package com.example.moviesdb.remote.mapper

import com.example.moviesdb.data.model.MovieResultEntity
import com.example.moviesdb.remote.model.MovieResultRemote
import java.lang.Exception
import javax.inject.Inject

class MovieResultEntityMapper @Inject constructor(
    private val movieEntityMapper: MovieEntityMapper
) {

    fun map(input: MovieResultRemote): MovieResultEntity {
        assertEssentialParams(remote = input)

        return MovieResultEntity(
            results = input.results!!.map { movieEntityMapper.map(it) }
        )
    }

    private fun assertEssentialParams(remote: MovieResultRemote) {
        if (remote.results == null) {
            throw Exception("Can't find remote.results in $remote")
        }
    }
}