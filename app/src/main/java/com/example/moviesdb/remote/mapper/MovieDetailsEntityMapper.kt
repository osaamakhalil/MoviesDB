package com.example.moviesdb.remote.mapper

import com.example.moviesdb.data.model.MovieDetailsEntity
import com.example.moviesdb.remote.model.MovieDetailsRemote
import java.lang.Exception
import javax.inject.Inject

class MovieDetailsEntityMapper @Inject constructor() {

    fun map(input: MovieDetailsRemote): MovieDetailsEntity {
        assertEssentialParams(remote = input)

        return MovieDetailsEntity(
            id = input.id!!,
            title = input.title!!,
            backImage = input.backImage!!,
            overview = input.overview!!,
            releaseDate = input.releaseDate!!,
            revenue = input.revenue!!,
            status = input.status!!,
            tagline = input.tagline!!
        )
    }

    private fun assertEssentialParams(remote: MovieDetailsRemote) {
        if (remote.id == null) {
            throw Exception("Can't find remote.id in $remote")
        }
        if (remote.title == null) {
            throw Exception("Can't find remote.title in $remote")
        }
        if (remote.backImage == null) {
            throw Exception("Can't find remote.backImage in $remote")
        }
        if (remote.overview == null) {
            throw Exception("Can't find remote.overview in $remote")
        }
        if (remote.releaseDate == null) {
            throw Exception("Can't find remote.releaseDate in $remote")
        }
        if (remote.revenue == null) {
            throw Exception("Can't find remote.revenue in $remote")
        }
        if (remote.status == null) {
            throw Exception("Can't find remote.status in $remote")
        }
        if (remote.tagline == null) {
            throw Exception("Can't find remote.tagline in $remote")
        }

    }
}