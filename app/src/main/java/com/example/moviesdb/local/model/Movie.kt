package com.example.moviesdb.local.model

import android.os.Parcelable
import androidx.room.Entity
import com.example.moviesdb.util.Constants.MOVIES_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = MOVIES_TABLE_NAME)
@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val backImage: String,
    val overview: String,
    val releaseDate: String,
    val revenue: Int,
    val status: String,
    val tagline: String
): Parcelable