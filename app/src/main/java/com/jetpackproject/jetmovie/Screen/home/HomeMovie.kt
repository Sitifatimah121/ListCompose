package com.jetpackproject.jetmovie.Screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackproject.jetmovie.MovieListItem
import com.jetpackproject.jetmovie.ViewModelFactory
import com.jetpackproject.jetmovie.di.Injection
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.util.State

@Composable
fun HomeMovie (
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
){
    viewModel.state.collectAsState(initial = State.Loading).value.let { state ->
        when(state){
            is State.Loading -> {
                viewModel.getAllMovie()
            }

            is State.Success -> {
                MovieContent(
                    movie = state.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is State.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieContent (
    movie: List<Movie>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    ){
        LazyColumn{
            items(movie) { movie ->
                MovieListItem(
                    title = movie.title,
                    genre = movie.genre,
                    photoUrl = movie.photoUrl,
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 200))
                        .clickable {
                        navigateToDetail(movie.id)
                    }
                )
                Log.d(TAG, "MovieClick: ${movie.id}")
            }
            Log.d(TAG, "MovieContent: ${movie.size}}")
        }

}
