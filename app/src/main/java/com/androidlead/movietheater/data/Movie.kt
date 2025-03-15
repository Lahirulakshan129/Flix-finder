package com.androidlead.movietheater.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val img: Int,
    val trailerUrl: String,
    val cate: String

)
