package com.androidlead.movietheater.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?, // Image URL
    @SerializedName("overview") val overview: String, // Description
    @SerializedName("release_date") val releaseDate: String, // Release date
    @SerializedName("vote_average") val rating: Double, // Rating
    val trailerUrl: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int> // Genre IDs

)