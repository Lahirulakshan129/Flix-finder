package com.androidlead.movietheater.data

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.Path

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String, // Correct query parameter name
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String, // Correct query parameter name
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<VideoResponse>
}


data class MovieResponse(
    val results: List<Movie>
)