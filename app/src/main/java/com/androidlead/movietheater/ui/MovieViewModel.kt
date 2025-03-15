package com.androidlead.movietheater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidlead.movietheater.data.Movie
import com.androidlead.movietheater.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    // StateFlow for movies
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    private val _upcoming = MutableStateFlow<List<Movie>>(emptyList())
    private val _action = MutableStateFlow<List<Movie>>(emptyList())
    private val _scifi = MutableStateFlow<List<Movie>>(emptyList())
    private val _animation = MutableStateFlow<List<Movie>>(emptyList())
    private val _mystery = MutableStateFlow<List<Movie>>(emptyList())
    private val _drama = MutableStateFlow<List<Movie>>(emptyList())
    private val _thriller = MutableStateFlow<List<Movie>>(emptyList())

    // Expose StateFlow to UI
    val movies: StateFlow<List<Movie>> = _movies
    val upcoming: StateFlow<List<Movie>> = _upcoming
    val action: StateFlow<List<Movie>> = _action
    val scifi: StateFlow<List<Movie>> = _scifi
    val animation: StateFlow<List<Movie>> = _animation
    val mystery: StateFlow<List<Movie>> = _mystery
    val drama: StateFlow<List<Movie>> = _drama
    val thriller: StateFlow<List<Movie>> = _thriller

    // Loading and error states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                // Fetch all movies from the API
                _movies.value = repository.getPopularMovies()

                // Fetch movies by category from the API
                _upcoming.value = repository.getUpcomingMovies()
                _action.value = repository.getActionMovies()
                _scifi.value = repository.getSciFiMovies()
                _animation.value = repository.getAnimationMovies()
                _mystery.value = repository.getMysteryMovies()
                _drama.value = repository.getDramaMovies()
                _thriller.value = repository.getThrillerMovies()
            } catch (e: Exception) {
                _error.value = "Failed to load movies: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMovies(): List<Movie> {
        return _movies.value
    }

    fun getAction(): List<Movie> {
        return _action.value
    }

    // Optional: Add or delete movies (e.g., for a favorites feature)
    fun addMovies(movieList: List<Movie>) {
        viewModelScope.launch {
            // If you want to cache movies locally, you can add them to a local database here.
            // Otherwise, you can skip this method or use it to update a local cache.
            loadMovies() // Refresh movies after insertion
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            // If you want to remove movies from a local cache, you can do so here.
            loadMovies() // Refresh movies after deletion
        }
    }
}