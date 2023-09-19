package com.teddy.example.compose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.teddy.example.compose.ui.theme.ComposeTheme
import com.teddy.example.compose.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//Android hilt EntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
            //자식을 수직 순서로 배치하는 레이아웃 구성 composable (기본값 세로)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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
                            Log.d("Teddy", "Click !!!!")
                            Log.d("Teddy", "textValue : ${textState.value}")
                            viewModel.getMovies(textState.value)
                        }
                    ) {
                        Text(text = "Search")
                    }
                }
                MovieList()
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect { event -> handleEvent(event) }
            }
        }

    }

    /*
     * Composable 의 함수들은 첫글자가 대문자여야한다.
     * UI 이기 때문에 반환 값은 없다.
     */
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MovieList() {
        // StateFlow 값 변경 감지 설정 (Observe)
        val movieList = viewModel.searchResult.collectAsState(initial = emptyList())
        LazyRow {
            itemsIndexed(
                items = movieList.value ?: emptyList()
            ) { _, movie ->
                Card(// Android CardView
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Card View Click

                        }
                ) {
                    Column {
                        GlideImage(
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
    }

    //event Handler
    private fun handleEvent(event: MovieViewModel.Event) = when (event) {
        is MovieViewModel.Event.GetMoviesError -> Toast.makeText(
            this,
            event.text,
            Toast.LENGTH_SHORT
        ).show()

        is MovieViewModel.Event.TooManyData -> Toast.makeText(this, event.text, Toast.LENGTH_SHORT)
            .show()

        is MovieViewModel.Event.NoSearchData -> Toast.makeText(this, event.text, Toast.LENGTH_SHORT)
            .show()
    }

}