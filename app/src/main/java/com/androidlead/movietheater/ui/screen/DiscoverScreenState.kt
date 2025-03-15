package com.androidlead.movietheater.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.androidlead.movietheater.MovieViewModel
import com.androidlead.movietheater.data.Movie
import kotlinx.coroutines.launch

data class DiscoverScreenState(
    val featuredMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val recentlyWatchedMovies: List<Movie> = emptyList(),
    val streamingMovies: List<Movie> = emptyList()
) {
    var movies by mutableStateOf(emptyList<Movie>())

    fun loadMovies(viewModel: MovieViewModel) {
        viewModel.viewModelScope.launch {
            movies = viewModel.getMovies()
        }
    }
}
