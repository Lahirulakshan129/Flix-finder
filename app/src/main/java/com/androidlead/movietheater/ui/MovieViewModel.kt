package com.androidlead.movietheater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidlead.movietheater.data.Movie
import com.androidlead.movietheater.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Thread.State

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    private val _upcoming = MutableStateFlow<List<Movie>>(emptyList())
    private val _action = MutableStateFlow<List<Movie>>(emptyList())
    private val _scifi = MutableStateFlow<List<Movie>>(emptyList())
    private val _animation = MutableStateFlow<List<Movie>>(emptyList())
    private val _mystery = MutableStateFlow<List<Movie>>(emptyList())
    private val _drama = MutableStateFlow<List<Movie>>(emptyList())
    private val _thriller = MutableStateFlow<List<Movie>>(emptyList())


    val movies: StateFlow<List<Movie>> = _movies
    val upcoming: StateFlow<List<Movie>> = _upcoming
    val action: StateFlow<List<Movie>> = _action
    val scifi: StateFlow<List<Movie>> = _scifi
    val animation: StateFlow<List<Movie>> = _animation
    val mystery: StateFlow<List<Movie>> = _mystery
    val drama: StateFlow<List<Movie>> = _drama
    val thriller: StateFlow<List<Movie>> = _thriller

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = repository.getAllMovies()
            _upcoming.value = repository.getUpcomingMovies()
            _action.value = repository.getActionMovies()
            _scifi.value = repository.getSciFiMovies()
            _animation.value = repository.getAnimationMovies()
            _mystery.value = repository.getMysteryMovies()
            _drama.value = repository.getDramaMovies()
            _thriller.value = repository.getThrillerMovies()
        }
    }

    fun getMovies(): List<Movie> {
        return _movies.value
    }

    fun getAction(): List<Movie> {
        return _action.value
    }

    fun addMovies(movieList: List<Movie>) {
        viewModelScope.launch {
            repository.insertMovies(movieList)
            loadMovies() // Refresh movies after insertion
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovie(movie)
            loadMovies()
        }
    }

}
