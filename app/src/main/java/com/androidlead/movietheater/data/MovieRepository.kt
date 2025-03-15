package com.androidlead.movietheater.data

import retrofit2.Response

class MovieRepository(private val apiService: MovieApiService) {

    private val baseImageUrl = "https://image.tmdb.org/t/p/w500" // Base URL for TMDb images
    private val youtubeBaseUrl = "https://www.youtube.com/watch?v="
    // Genre IDs for TMDb
    private val actionGenreId = 28
    private val scifiGenreId = 878
    private val animationGenreId = 16
    private val mysteryGenreId = 9648
    private val dramaGenreId = 18
    private val thrillerGenreId = 53

    // Fetch popular movies
    suspend fun getPopularMovies(): List<Movie> {
        val response = apiService.getPopularMovies("2e7443e8b6e07b797865aa72ac9a7029")
        return if (response.isSuccessful) {
            response.body()?.results?.map { movie ->
                // Construct the full image URL
                val posterPath = movie.posterPath?.let { "$baseImageUrl$it" }
                // Fetch trailer URL
                val trailerUrl = fetchTrailerUrl(movie.id)
                movie.copy(posterPath = posterPath, trailerUrl = trailerUrl)
            } ?: emptyList()
        } else {
            emptyList()
        }
    }

    private suspend fun fetchTrailerUrl(movieId: Int): String? {
        val response = apiService.getMovieVideos(movieId, "2e7443e8b6e07b797865aa72ac9a7029")
        return if (response.isSuccessful) {
            val videos = response.body()?.results
            // Prioritize official trailers from YouTube
            videos
                ?.firstOrNull { video -> video.type == "Trailer" && video.site == "YouTube" }
                ?.let { video -> "$youtubeBaseUrl${video.key}" } // Construct YouTube URL
        } else {
            null
        }
    }
    // Fetch upcoming movies
    suspend fun getUpcomingMovies(): List<Movie> {
        val response = apiService.getUpcomingMovies("2e7443e8b6e07b797865aa72ac9a7029")
        return if (response.isSuccessful) {
            response.body()?.results?.map { movie ->
                // Construct the full image URL
                val posterPath = movie.posterPath?.let { "$baseImageUrl$it" }
                // Fetch trailer URL
                val trailerUrl = fetchTrailerUrl(movie.id)
                movie.copy(posterPath = posterPath, trailerUrl = trailerUrl)
            } ?: emptyList()
        } else {
            emptyList()
        }
    }
    // Fetch action movies
    suspend fun getActionMovies(): List<Movie> {
        val movies = getPopularMovies()
        return movies.filter { movie -> actionGenreId in (movie.genreIds ?: emptyList()) }
    }

    // Fetch sci-fi movies
    suspend fun getSciFiMovies(): List<Movie> {
        val movies = getPopularMovies()
        return movies.filter { movie -> scifiGenreId in (movie.genreIds ?: emptyList()) }
    }

    // Fetch animation movies
    suspend fun getAnimationMovies(): List<Movie> {
        val movies = getPopularMovies()
        return movies.filter { movie -> animationGenreId in (movie.genreIds ?: emptyList()) }
    }

    // Fetch mystery movies
    suspend fun getMysteryMovies(): List<Movie> {
        val movies = getPopularMovies()
        return movies.filter { movie -> mysteryGenreId in (movie.genreIds ?: emptyList()) }
    }

    // Fetch drama movies
    suspend fun getDramaMovies(): List<Movie> {
        val movies = getPopularMovies()
        return movies.filter { movie -> dramaGenreId in (movie.genreIds ?: emptyList()) }
    }

    // Fetch thriller movies
    suspend fun getThrillerMovies(): List<Movie> {
        val movies = getPopularMovies()
        return movies.filter { movie -> thrillerGenreId in (movie.genreIds ?: emptyList()) }
    }

    // Helper function to fetch movies and handle errors
    private suspend fun fetchMovies(apiCall: suspend () -> Response<MovieResponse>): List<Movie> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.results?.map { movie ->
                    // Construct the full image URL
                    movie.copy(posterPath = movie.posterPath?.let { "$baseImageUrl$it" })
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            // Log the error or handle it as needed
            emptyList()
        }
    }
}