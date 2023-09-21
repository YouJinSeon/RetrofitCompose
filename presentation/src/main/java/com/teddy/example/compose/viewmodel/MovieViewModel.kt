package com.teddy.example.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teddy.example.domain.model.SearchEntity
import com.teddy.example.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoveUseCase: GetMovieUseCase
): ViewModel() {

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _searchResults: MutableStateFlow<List<SearchEntity>?> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<SearchEntity>?> = _searchResults

    fun getMovies(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMoveUseCase.invoke(title).onStart {

            }.catch {
                Log.d("MovieViewModel","SearchError @@@ ${it.toString()}")
            }.collect {
                if (it?.search.isNullOrEmpty()) {
                    var message = ""
                    when (it?.search) {
                        null -> message = "검색 결과 없습니다. 다시 시도해주세요."
                        else -> message = "검색 결과가 너무 많습니다. 다시 시도해주세요."
                    }

                    _searchResults.value = emptyList()
                    _errorMessage.value = message
                } else {
                    _errorMessage.value = ""
                    _searchResults.value = it?.search
                    Log.d("MovieViewModel", "Search Success : ${_searchResults.value}")
                }
            }
        }
    }
}