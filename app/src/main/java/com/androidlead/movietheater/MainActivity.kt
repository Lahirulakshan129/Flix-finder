package com.androidlead.movietheater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.androidlead.movietheater.data.Movie
import com.androidlead.movietheater.data.MovieDatabase
import com.androidlead.movietheater.data.MovieRepository
import com.androidlead.movietheater.ui.screen.DiscoverScreen
import com.androidlead.movietheater.ui.theme.MovieTheaterTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = MovieDatabase.getDatabase(this)
        val repository = MovieRepository(db.movieDao())
        viewModel = MovieViewModel(repository)
//
//        lifecycleScope.launch {
//            viewModel.movies.collectLatest { movieList ->
//                if (viewModel.getMovies().isEmpty()) {
//                    viewModel.addMovies(
//                        listOf(
//                            Movie(
//                                title = "Frozen 2",
//                                img = R.drawable.frozen_2,
//                                trailerUrl = "https://www.youtube.com/watch?v=bwzLiQZDw2I",
//                                cate = "Animation"
//                            ),
//                            Movie(
//                                title = "Knives Out",
//                                img = R.drawable.knives_out,
//                                trailerUrl = "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
//                                cate = "Mystery"
//                            ),
//                            Movie(
//                                title = "Parasite",
//                                img = R.drawable.parasite,
//                                trailerUrl = "https://www.youtube.com/watch?v=SEUXfv87Wpk",
//                                cate = "Thriller"
//                            ),
//                            Movie(
//                                title = "Midway",
//                                img = R.drawable.midway,
//                                trailerUrl = "https://www.youtube.com/watch?v=l9laReRAYFk",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "Weathering with You",
//                                img = R.drawable.weathering_with_you,
//                                trailerUrl = "https://www.youtube.com/watch?v=rVnDYM2ql1o",
//                                cate = "Animation"
//                            ),
//                            Movie(
//                                title = "The Good Liar",
//                                img = R.drawable.the_good_liar,
//                                trailerUrl = "https://www.youtube.com/watch?v=U2xDIe01fFY",
//                                cate = "Crime"
//                            ),
//                            Movie(
//                                title = "A Beautiful Day in the Neighborhood",
//                                img = R.drawable.a_beautiful_day,
//                                trailerUrl = "https://www.youtube.com/watch?v=UkrvyD0dUlM",
//                                cate = "Drama"
//                            ),
//                            Movie(
//                                title = "Charlie's Angels",
//                                img = R.drawable.charlies_angels,
//                                trailerUrl = "https://www.youtube.com/watch?v=vdthyoQ-RxA",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "Ford vs Ferrari",
//                                img = R.drawable.ford_vs_ferrari,
//                                trailerUrl = "https://www.youtube.com/watch?v=zyYgDtY2AMY",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "Interstellar",
//                                img = R.drawable.interstellar,
//                                trailerUrl = "https://www.youtube.com/watch?v=2LqzF5WauAw",
//                                cate = "ScienceFiction"
//                            ),
//                            Movie(
//                                title = "Invasion",
//                                img = R.drawable.invasion,
//                                trailerUrl = "https://www.youtube.com/watch?v=W4HpRKLw42Y",
//                                cate = "ScienceFiction"
//                            ),
//                            Movie(
//                                title = "The Creator",
//                                img = R.drawable.the_creator,
//                                trailerUrl = "https://www.youtube.com/watch?v=2Gq8TT-LHeA",
//                                cate = "ScienceFiction"
//                            ),
//                            Movie(
//                                title = "Dune",
//                                img = R.drawable.dune,
//                                trailerUrl = "https://www.youtube.com/watch?v=Pn6eOaI5HTk",
//                                cate = "ScienceFiction"
//                            ),
//                            Movie(
//                                title = "Alien",
//                                img = R.drawable.alien,
//                                trailerUrl = "https://www.youtube.com/watch?v=LjLamj-b0I8",
//                                cate = "ScienceFiction"
//                            ),
//                            Movie(
//                                title = "Inception",
//                                img = R.drawable.inception,
//                                trailerUrl = "https://www.youtube.com/watch?v=YoHD9XEInc0",
//                                cate = "ScienceFiction"
//                            ),
//                            Movie(
//                                title = "Fury",
//                                img = R.drawable.fury,
//                                trailerUrl = "https://www.youtube.com/watch?v=DsEm3G7AwAI",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "Crow",
//                                img = R.drawable.crow,
//                                trailerUrl = "https://www.youtube.com/watch?v=JtXI1BEwXVs",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "The Dark Knight Rises",
//                                img = R.drawable.the_dark_knight_rises,
//                                trailerUrl = "https://www.youtube.com/watch?v=EXeTwQWrcwY",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "The Beekeeper",
//                                img = R.drawable.the_beekeeper,
//                                trailerUrl = "https://www.youtube.com/watch?v=HqmkW1NnW9U",
//                                cate = "Action"
//                            ),
//                            Movie(
//                                title = "Batman Part 2",
//                                img = R.drawable.bat_man_part_2,
//                                trailerUrl = "https://www.youtube.com/watch?v=KdA82prVlAw",
//                                cate = "Upcoming"
//                            ),
//                            Movie(
//                                title = "Jurassic World Rebirth",
//                                img = R.drawable.jurassic_world_rebirth,
//                                trailerUrl = "https://www.youtube.com/watch?v=jan5CFWs9ic",
//                                cate = "Upcoming"
//                            )
//                        )
//                    )
//                }
//            }
//        }

        viewModel.loadMovies()

//        viewModel.addMovies(
//            listOf(
//                Movie(title = "", img = R.drawable.img_movie_poster_0, trailerUrl = "https://youtube.com/"))
//        )

//      viewModel.deleteMovie(Movie(id = 6, title = "Inception", img = R.drawable.img_movie_poster_0, trailerUrl = "https://youtube.com/"))

        setContent {
            MovieTheaterTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    DiscoverScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
