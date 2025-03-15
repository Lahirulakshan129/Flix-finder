package com.androidlead.movietheater.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE cate LIKE 'Upcoming'")
    fun getUpcomingMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Upcoming'")
    suspend fun getUpcomingMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Action'")
    fun getActionMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Action'")
    suspend fun getActionMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE cate LIKE 'ScienceFiction'")
    fun getSciFiMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'ScienceFiction'")
    suspend fun getSciFiMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Animation'")
    fun getAnimationMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Animation'")
    suspend fun getAnimationMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Mystery'")
    fun getMysteryMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Mystery'")
    suspend fun getMysteryMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Drama'")
    fun getDramaMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Drama'")
    suspend fun getDramaMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Thriller'")
    fun getThrillerMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE cate LIKE 'Thriller'")
    suspend fun getThrillerMovies(): List<Movie>

}
