package com.jetpackproject.jetmovie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.model.MovieData
import com.jetpackproject.jetmovie.ui.theme.JetMovieTheme

@Composable
fun MovieListItem(
    movieId: Int,
    title: String,
    genre: String,
    photoUrl: String,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ) {
        Column() {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)
            )
        }

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                )

            Text(
                text = genre,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview(){
    JetMovieTheme {
        MovieListItem(
            movieId = 1,
            title = "Paradise City",
            genre = "Fantasy",
            photoUrl = "https://github.com/Sitifatimah121/JetHeroes/blob/Sitifatimah121-assets/paradise%20city.jpeg?raw=true",
        )
    }
}