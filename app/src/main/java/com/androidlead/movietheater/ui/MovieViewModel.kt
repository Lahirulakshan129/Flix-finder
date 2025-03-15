package com.androidlead.movietheater

import android.util.Log
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
    private var hasLoadedMovies = false

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
        if (hasLoadedMovies) return
        hasLoadedMovies = true
        viewModelScope.launch {
            val allMovies = repository.getAllMovies()
            Log.d("MoviesCheck", "Loaded movies: ${allMovies.size}")

            if (allMovies.isEmpty()) {
                Log.d("MoviesCheck", "Adding default movies...")
                addMovies( listOf(
                    Movie(
                        title = "Frozen 2",
                        img = R.drawable.frozen_2,
                        trailerUrl = "https://www.youtube.com/watch?v=bwzLiQZDw2I",
                        cate = "Animation"
                    ),
                    Movie(
                        title = "Knives Out",
                        img = R.drawable.knives_out,
                        trailerUrl = "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
                        cate = "Mystery"
                    ),
                    Movie(
                        title = "Parasite",
                        img = R.drawable.parasite,
                        trailerUrl = "https://www.youtube.com/watch?v=SEUXfv87Wpk",
                        cate = "Thriller"
                    ),
                    Movie(
                        title = "Midway",
                        img = R.drawable.midway,
                        trailerUrl = "https://www.youtube.com/watch?v=l9laReRAYFk",
                        cate = "Action"
                    ),
                    Movie(
                        title = "Weathering with You",
                        img = R.drawable.weathering_with_you,
                        trailerUrl = "https://www.youtube.com/watch?v=rVnDYM2ql1o",
                        cate = "Animation"
                    ),
                    Movie(
                        title = "The Good Liar",
                        img = R.drawable.the_good_liar,
                        trailerUrl = "https://www.youtube.com/watch?v=U2xDIe01fFY",
                        cate = "Crime"
                    ),
                    Movie(
                        title = "A Beautiful Day in the Neighborhood",
                        img = R.drawable.a_beautiful_day,
                        trailerUrl = "https://www.youtube.com/watch?v=UkrvyD0dUlM",
                        cate = "Drama"
                    ),
                    Movie(
                        title = "Charlie's Angels",
                        img = R.drawable.charlies_angels,
                        trailerUrl = "https://www.youtube.com/watch?v=vdthyoQ-RxA",
                        cate = "Action"
                    ),
                    Movie(
                        title = "Ford vs Ferrari",
                        img = R.drawable.ford_vs_ferrari,
                        trailerUrl = "https://www.youtube.com/watch?v=zyYgDtY2AMY",
                        cate = "Action"
                    ),
                    Movie(
                        title = "Interstellar",
                        img = R.drawable.interstellar,
                        trailerUrl = "https://www.youtube.com/watch?v=2LqzF5WauAw",
                        cate = "ScienceFiction"
                    ),
                    Movie(
                        title = "Invasion",
                        img = R.drawable.invasion,
                        trailerUrl = "https://www.youtube.com/watch?v=W4HpRKLw42Y",
                        cate = "ScienceFiction"
                    ),
                    Movie(
                        title = "The Creator",
                        img = R.drawable.the_creator,
                        trailerUrl = "https://www.youtube.com/watch?v=2Gq8TT-LHeA",
                        cate = "ScienceFiction"
                    ),
                    Movie(
                        title = "Dune",
                        img = R.drawable.dune,
                        trailerUrl = "https://www.youtube.com/watch?v=Pn6eOaI5HTk",
                        cate = "ScienceFiction"
                    ),
                    Movie(
                        title = "Alien",
                        img = R.drawable.alien,
                        trailerUrl = "https://www.youtube.com/watch?v=LjLamj-b0I8",
                        cate = "ScienceFiction"
                    ),
                    Movie(
                        title = "Inception",
                        img = R.drawable.inception,
                        trailerUrl = "https://www.youtube.com/watch?v=YoHD9XEInc0",
                        cate = "ScienceFiction"
                    ),
                    Movie(
                        title = "Fury",
                        img = R.drawable.fury,
                        trailerUrl = "https://www.youtube.com/watch?v=DsEm3G7AwAI",
                        cate = "Action"
                    ),
                    Movie(
                        title = "Crow",
                        img = R.drawable.crow,
                        trailerUrl = "https://www.youtube.com/watch?v=JtXI1BEwXVs",
                        cate = "Action"
                    ),
                    Movie(
                        title = "The Dark Knight Rises",
                        img = R.drawable.the_dark_knight_rises,
                        trailerUrl = "https://www.youtube.com/watch?v=EXeTwQWrcwY",
                        cate = "Action"
                    ),
                    Movie(
                        title = "The Beekeeper",
                        img = R.drawable.the_beekeeper,
                        trailerUrl = "https://www.youtube.com/watch?v=HqmkW1NnW9U",
                        cate = "Action"
                    ),
                    Movie(
                        title = "Batman Part 2",
                        img = R.drawable.bat_man_part_2,
                        trailerUrl = "https://www.youtube.com/watch?v=KdA82prVlAw",
                        cate = "Upcoming"
                    ),
                    Movie(
                        title = "Jurassic World Rebirth",
                        img = R.drawable.jurassic_world_rebirth,
                        trailerUrl = "https://www.youtube.com/watch?v=jan5CFWs9ic",
                        cate = "Upcoming"
                    )
                ))
            }

            _movies.value = allMovies
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
