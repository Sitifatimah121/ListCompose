package com.jetpackproject.jetmovie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jetpackproject.jetmovie.view.detail.DetailViewModel
import com.jetpackproject.jetmovie.di.Injection
import com.jetpackproject.jetmovie.ui.theme.JetMovieTheme
import com.jetpackproject.jetmovie.util.State

@Composable
fun DetailMovie (
    movieId: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
){
    viewModel.state.collectAsState(initial = State.Loading).value.let { state ->
        when(state){
            is State.Loading -> {
                viewModel.getMovieById(movieId)
            }
            is State.Success -> {
                val data = state.data
                DetailContent(
                    title = data.title,
                    genre = data.genre,
                    photoUrl = data.photoUrl,
                    realease = data.realease,
                    description = data.description,
                )
            }
            is State.Error -> {}
        }
    }
}
@Composable
fun DetailContent(
    title: String,
    genre: String,
    photoUrl: String,
    realease: String,
    description: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(16.dp)
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Column() {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(end = 16.dp)
                )

                Text(
                    text = genre,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 3.dp)
                )

                Text(
                    text = realease,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }

        }

            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Description",
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailMoviePreview(){
    JetMovieTheme {
        DetailContent(
            "R.I.P.D. 2: Rise of the Damned",
            "action",
            "https://github.com/Sitifatimah121/JetHeroes/blob/Sitifatimah121-assets/Rise%20of%20the%20Damned.jpeg?raw=true",
            "date",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
    }
}