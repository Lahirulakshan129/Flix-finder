package com.androidlead.movietheater.data

import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: Flow<List<Movie>> = movieDao.getAllMoviesFlow()
    val upcoming: Flow<List<Movie>> = movieDao.getUpcomingMoviesFlow()
    val action: Flow<List<Movie>> = movieDao.getActionMoviesFlow()
    val scifi: Flow<List<Movie>> = movieDao.getSciFiMoviesFlow()
    val animation: Flow<List<Movie>> = movieDao.getAnimationMoviesFlow()
    val mystery: Flow<List<Movie>> = movieDao.getMysteryMoviesFlow()
    val drama: Flow<List<Movie>> = movieDao.getDramaMoviesFlow()
    val thriller: Flow<List<Movie>> = movieDao.getThrillerMoviesFlow()

    suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        return movieDao.getUpcomingMovies()
    }

    suspend fun getActionMovies(): List<Movie> {
        return movieDao.getActionMovies()
    }

    suspend fun getAnimationMovies(): List<Movie> {
        return movieDao.getAnimationMovies()
    }

    suspend fun getSciFiMovies(): List<Movie> {
        return movieDao.getSciFiMovies()
    }

    suspend fun getMysteryMovies(): List<Movie> {
        return movieDao.getMysteryMovies()
    }

    suspend fun getDramaMovies(): List<Movie> {
        return movieDao.getDramaMovies()
    }

    suspend fun getThrillerMovies(): List<Movie> {
        return movieDao.getThrillerMovies()
    }

    suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

}
