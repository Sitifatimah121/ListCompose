package com.jetpackproject.jetmovie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackproject.jetmovie.ui.theme.JetMovieTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ima),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Siti Fatimah",
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
                .padding(top = 15.dp)
        )
        
        Text(
            text = "sitifatimah.13@gmail.com"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilScreenPreview(){
    JetMovieTheme {
        ProfileScreen()
    }
}