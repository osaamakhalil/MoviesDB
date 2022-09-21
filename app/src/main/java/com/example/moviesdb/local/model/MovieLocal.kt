package com.example.moviesdb.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesdb.util.Constants.MOVIES_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = MOVIES_TABLE_NAME)
@Parcelize
data class MovieLocal(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String
): Parcelable