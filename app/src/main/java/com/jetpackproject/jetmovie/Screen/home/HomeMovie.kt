package com.jetpackproject.jetmovie.Screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackproject.jetmovie.MovieListItem
import com.jetpackproject.jetmovie.ViewModelFactory
import com.jetpackproject.jetmovie.di.Injection
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.ui.SearchBar
import com.jetpackproject.jetmovie.util.UiState

@Composable
fun HomeMovie (
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
){
    val query by viewModel.query
    viewModel.state.collectAsState(initial = UiState.Loading).value.let { state ->
        when(state){
            is UiState.Loading -> {
                viewModel.getAllMovie()
            }

            is UiState.Success -> {
                MovieContent(
                    query = query,
                    onQueryChange = viewModel::searchMovie,
                    listMovie = state.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun MovieContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listMovie: List<Movie>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
){
    Column() {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange )

        ListMovie(
            movie = listMovie,
            navigateToDetail = navigateToDetail,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListMovie (
    movie: List<Movie>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    ){
        LazyColumn(modifier = modifier){
            items(movie) { movie ->
                MovieListItem(
                    movieId = movie.id,
                    title = movie.title,
                    genre = movie.genre,
                    photoUrl = movie.photoUrl,
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 200))
                        .clickable {
                            navigateToDetail(movie.id)
                        }
                )
            }
        }

}
