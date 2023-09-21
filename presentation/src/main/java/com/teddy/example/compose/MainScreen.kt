package com.teddy.example.compose

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.teddy.example.compose.navigation.navigateMovieDetail
import com.teddy.example.compose.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    // Compose 뒤로가기 설정
    BackOnPressed()
    val viewModel: MovieViewModel = hiltViewModel()
    // StateFlow 값 변경 감지 설정 (Observe)
    val movieList = viewModel.searchResult.collectAsState(initial = emptyList())
    val errorMessage = viewModel.errorMessage.collectAsState()
    //자식을 수직 순서로 배치하는 레이아웃 구성 composable (기본값 세로)
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // 병렬 layout 처리
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            /*
            * editText 의 Recompose 가 발생되기 전 State 를 저장하고 Recompose 가 일어낫을 때 복구하기 위함
            * remember 을 선언해주지 않으면 textField 에 타이핑을 하여도 글자가 입력되지 않는다.
            * */
            val textState = remember { mutableStateOf("") }
            //editText
            TextField(
                value = textState.value,
                onValueChange = { textValue -> textState.value = textValue })

            Button(
                onClick = {
                    viewModel.getMovies(title = textState.value)
                }
            ) {
                Text(text = "Search")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
//            LazyRow {// 가로
            LazyColumn {
                itemsIndexed(
                    items = movieList.value!!
                ) { _, movie ->
                    Card(// Android CardView
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Card View Click
                                navController.navigateMovieDetail(movie.imdbID)
                            }
                    ) {
                        Column {
                            GlideImage (
                                model = movie?.poster,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(10.dp),
                                contentScale = ContentScale.Crop,
                            )
                            Text(
                                text = movie?.title.toString(),
                                modifier = Modifier.padding(3.dp),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = movie?.year.toString(),
                                modifier = Modifier.padding(3.dp),
                            )
                        }
                    }
                }
            }
            if (errorMessage.value != "") {
                Text (
                    text = errorMessage.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun BackOnPressed() {
    val context = LocalContext.current
    var backPressedState = remember { mutableStateOf(true) }
    var backPressedTime = 0L

    BackHandler(enabled = backPressedState.value) {
        if(System.currentTimeMillis() - backPressedTime <= 400L) {
            // 앱 종료
            (context as Activity).finish()
        } else {
            backPressedState.value = true
            Toast.makeText(context, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}