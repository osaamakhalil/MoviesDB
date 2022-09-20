package com.example.moviesdb.remote.mapper

import com.example.moviesdb.data.model.MovieEntity
import com.example.moviesdb.remote.model.MovieRemote
import java.lang.Exception
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() {

    fun map(input: MovieRemote): MovieEntity {
        assertEssentialParams(remote = input)

        return MovieEntity(
            id = input.id!!,
            overview = input.overview!!,
            poster = input.poster!!,
            releaseDate = input.releaseDate!!,
            title = input.title!!
        )
    }

    private fun assertEssentialParams(remote: MovieRemote) {
        if (remote.id == null) {
            throw Exception("Can't find remote.id in $remote")
        }
        if (remote.overview == null) {
            throw Exception("Can't find remote.overview in $remote")
        }
        if (remote.poster == null) {
            throw Exception("Can't find remote.poster in $remote")
        }
        if (remote.releaseDate == null) {
            throw Exception("Can't find remote.releaseDate in $remote")
        }
        if (remote.title == null) {
            throw Exception("Can't find remote.title in $remote")
        }
    }
}