package com.example.medialibary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import com.example.medialibary.models.BoardGame
import com.example.medialibary.models.Book
import com.example.medialibary.models.Movie
import com.example.medialibary.models.VideoGame
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "media.database"
        ).build()

        setContent {
            val navController = rememberNavController()
            val movies = remember { mutableStateListOf<Movie>() }
            val books = remember { mutableStateListOf<Book>() }
            val boardGames = remember { mutableStateListOf<BoardGame>() }
            val videoGames = remember { mutableStateListOf<VideoGame>() }

            LaunchedEffect(Unit) {
                launch(Dispatchers.IO) {
                    movies += db.moviesDao.getAllMovies()
                    books += db.booksDao.getAllBooks()
                    boardGames += db.boardGamesDao.getAllBoardGames()
                    videoGames += db.videoGamesDao.getAllVideoGames()
                }
            }

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
                            modifier = Modifier.fillMaxWidth()
                                .clickable { navController.navigate(Destinations.Home) },
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
                                onVideoGameClick = { id ->
                                    navController.navigate(Destinations.VideoGame(id?: 0))
                                },
                                onAddVideoGameClick = { navController.navigate(Destinations.CreateVideoGame) }
                            )
                        }
                        composable<Destinations.Movies> {
                            MoviesScreen(
                                onMovieClick = { id ->
                                    navController.navigate(Destinations.Movie(id?: 0))
                                },
                                onAddMovieClick = { navController.navigate(Destinations.CreateMovie) }
                            )
                        }
                        composable<Destinations.BoardGames> {
                            BoardGamesScreen(
                                onBoardGameClick = { id ->
                                    navController.navigate(Destinations.BoardGame(id?: 0))
                                },
                                onAddBoardGameClick = { navController.navigate(Destinations.CreateBoardGame) }
                            )
                        }
                        composable<Destinations.Books> {
                            BooksScreen(
                                onBookClick = { id ->
                                    navController.navigate(
                                        Destinations.Book(id?: 0)
                                    )
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
                                goBack = { navController.popBackStack() },
                                id = route.id
                            )
                        }
                        composable<Destinations.Movie> {
                            val route = it.toRoute<Destinations.Movie>()
                            MovieScreen(
                                goBack = { navController.popBackStack() },
                                id = route.id
                            )
                        }
                        composable<Destinations.BoardGame> {
                            val route = it.toRoute<Destinations.BoardGame>()
                            BoardGameScreen(
                                goBack = { navController.popBackStack() },
                                id = route.id
                            )
                        }
                        composable<Destinations.Book> {
                            val route = it.toRoute<Destinations.Book>()
                            BookScreen(
                                goBack = { navController.popBackStack() },
                                id = route.id
                            )
                        }
                    }
                }
            }
        }
    }
}
