package com.example.moviesdb.local.dao

import androidx.room.*
import com.example.moviesdb.local.model.MovieLocal
import com.example.moviesdb.util.Constants.MOVIES_TABLE_NAME
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM $MOVIES_TABLE_NAME")
    fun getMovie(): Single<List<MovieLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieLocal): Completable

    @Delete
    fun removeMovie(movie: MovieLocal): Completable
}