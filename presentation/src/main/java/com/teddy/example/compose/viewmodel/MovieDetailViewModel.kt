package com.teddy.example.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teddy.example.domain.model.MovieEntity
import com.teddy.example.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): ViewModel() {

    private val _detailMovieResult: MutableStateFlow<MovieEntity?> = MutableStateFlow(null)
    val detailMovieResult: StateFlow<MovieEntity?> = _detailMovieResult
    fun detailMovie(imdbID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMovieDetailUseCase.invoke(imdbID).collect {
                it.let {
                    _detailMovieResult.value= it
                    Log.d("MovieDetailViewModel", "detail data : $it")
                }
            }
        }
    }

}