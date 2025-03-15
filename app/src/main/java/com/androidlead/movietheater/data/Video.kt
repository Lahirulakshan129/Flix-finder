package com.androidlead.movietheater.data

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("key") val key: String, // YouTube video ID
    @SerializedName("type") val type: String, // Video type (e.g., "Trailer")
    @SerializedName("site") val site: String // Video site (e.g., "YouTube")
)