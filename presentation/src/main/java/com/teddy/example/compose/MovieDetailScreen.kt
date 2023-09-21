package com.teddy.example.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.teddy.example.compose.viewmodel.MovieDetailViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailScreen(
    imdbID: String
) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    viewModel.detailMovie(imdbID)
    val detailMovieResult = viewModel.detailMovieResult.collectAsState()
    val scrollState = rememberScrollState() // scrollState (아래로 스크롤 시 사용)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        GlideImage (
            model = detailMovieResult?.value?.Poster,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(10.dp)
        )
        Text(
            text = "Actors : ${detailMovieResult?.value?.Actors ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Country : ${detailMovieResult?.value?.Country ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Country : ${detailMovieResult?.value?.Country ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Director : ${detailMovieResult?.value?.Director ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Genre : ${detailMovieResult?.value?.Genre ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Year : ${detailMovieResult?.value?.Year ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Title : ${detailMovieResult?.value?.Title ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Writer : ${detailMovieResult?.value?.Writer ?: ""}",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
        Text(
            text = "Rating : ${detailMovieResult?.value?.imdbRating ?: "0"}/10",
            modifier = Modifier.padding(3.dp),
            fontSize = 14.sp
        )
    }

}