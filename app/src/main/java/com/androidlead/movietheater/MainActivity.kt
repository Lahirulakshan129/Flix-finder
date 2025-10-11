package com.androidlead.movietheater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.androidlead.movietheater.data.MovieRepository
import com.androidlead.movietheater.data.RetrofitClient
import com.androidlead.movietheater.ui.screen.DiscoverScreen
import com.androidlead.movietheater.ui.theme.MovieTheaterTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MovieViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val apiService = RetrofitClient.movieApiService

        // Create MovieRepository with the API service
        val repository = MovieRepository(apiService)

        // Initialize ViewModel with the repository
        viewModel = MovieViewModel(repository)

        // Load movies from the API
        viewModel.loadMovies()

        // Set up the UI
        setContent {
            MovieTheaterTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.image),
                                        contentDescription = "FlixFinder Logo",
                                         modifier = Modifier.size(width = 300.dp, height = 150.dp) // Adjust size as needed
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                               // containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                containerColor = Color(0x30000000)
                            )
                        )
                    }
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