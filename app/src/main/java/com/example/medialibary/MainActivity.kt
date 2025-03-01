package com.example.medialibary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.medialibary.ui.screens.BoardGameScreen
import com.example.medialibary.ui.screens.BoardGamesScreen
import com.example.medialibary.ui.screens.BookScreen
import com.example.medialibary.ui.screens.BooksScreen
import com.example.medialibary.ui.screens.CreateBoardGameScreen
import com.example.medialibary.ui.screens.CreateBookScreen
import com.example.medialibary.ui.screens.CreateMovieScreen
import com.example.medialibary.ui.screens.CreateVideoGameScreen
import com.example.medialibary.ui.screens.HomeScreen
import com.example.medialibary.ui.screens.MovieScreen
import com.example.medialibary.ui.screens.MoviesScreen
import com.example.medialibary.ui.screens.VideoGameScreen
import com.example.medialibary.ui.screens.VideoGamesScreen
import com.example.medialibary.ui.theme.MediaLibaryTheme
import com.example.medialibary.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MediaLibaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Media Library") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Destinations.Home> {
                            HomeScreen(
                                onVideoGamesClick = { navController.navigate(Destinations.VideoGames) },
                                onMoviesClick = { navController.navigate(Destinations.Movies) },
                                onBoardGamesClick = { navController.navigate(Destinations.BoardGames) },
                                onBooksClick = { navController.navigate(Destinations.Books) },
                            )
                        }
                        composable<Destinations.VideoGames> {
                            VideoGamesScreen(
                                onVideoGameClick = {
                                    navController.navigate(Destinations.VideoGame(it))
                                },
                                onAddVideoGameClick = { navController.navigate(Destinations.CreateVideoGame) }
                            )
                        }
                        composable<Destinations.Movies> {
                            MoviesScreen(
                                onMovieClick = {
                                    navController.navigate(Destinations.Movie(it))
                                },
                                onAddMovieClick = { navController.navigate(Destinations.CreateMovie) }
                            )
                        }
                        composable<Destinations.BoardGames> {
                            BoardGamesScreen(
                                onBoardGameClick = {
                                    navController.navigate(Destinations.BoardGame(it))
                                },
                                onAddBoardGameClick = { navController.navigate(Destinations.CreateBoardGame) }
                            )
                        }
                        composable<Destinations.Books> {
                            BooksScreen(
                                onBookClick = {
                                    navController.navigate(Destinations.Book(it))
                                },
                                onAddBookClick = { navController.navigate(Destinations.CreateBook) }
                            )
                        }
                        dialog<Destinations.CreateVideoGame> {
                            CreateVideoGameScreen(
                                goBack = { navController.popBackStack() }
                            )
                        }
                        dialog<Destinations.CreateBook> {
                            CreateBookScreen(
                                goBack = { navController.popBackStack() }
                            )
                        }
                        dialog<Destinations.CreateMovie> {
                            CreateMovieScreen(
                                goBack = { navController.popBackStack() }
                            )
                        }
                        dialog<Destinations.CreateBoardGame> {
                            CreateBoardGameScreen(
                                goBack = { navController.popBackStack() }
                            )
                        }
                        composable<Destinations.VideoGame> {
                            val route = it.toRoute<Destinations.VideoGame>()
                            VideoGameScreen(
                                id = route.id
                            )
                        }
                        composable<Destinations.Movie> {
                            val route = it.toRoute<Destinations.Movie>()
                            MovieScreen(
                                id = route.id
                            )
                        }
                        composable<Destinations.BoardGame> {
                            val route = it.toRoute<Destinations.BoardGame>()
                            BoardGameScreen(
                                id = route.id
                            )
                        }
                        composable<Destinations.Book> {
                            val route = it.toRoute<Destinations.Book>()
                            BookScreen(
                                id = route.id
                            )
                        }
                    }
                }
            }
        }
    }
}
