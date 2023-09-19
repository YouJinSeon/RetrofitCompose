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

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _searchResults: MutableStateFlow<List<SearchEntity>?> = MutableStateFlow(null)
    val searchResult: StateFlow<List<SearchEntity>?> = _searchResults

    fun getMovies(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMoveUseCase.invoke(title).onStart {

            }.catch {
                Log.d("MovieViewModel","SearchError @@@ ${it.toString()}")
            }.collect {
                if (it?.search.isNullOrEmpty()) {
                    val emptyItem: List<SearchEntity> = arrayListOf<SearchEntity>().apply {
                        this.add(
                            SearchEntity(
                                title = "No Search Data\nPlease search again",
                                year = "",
                                poster = "",
                                imdbID = ""
                            )
                        )
                    }

                    when (it?.search) {
                        null -> noSearchData()
                        else -> tooManyData()
                    }

                    _searchResults.value = emptyItem
                } else {
                    _searchResults.value = it?.search
                    Log.d("MovieViewModel", "Success : ${_searchResults.value}")
                }
            }
        }
    }

    private fun tooManyData() {
        event(Event.TooManyData("There are too many search results"))
    }

    private fun noSearchData() {
        event(Event.NoSearchData("No results were found for your search"))
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class GetMoviesError(val text: String) : Event()
        data class TooManyData(val text: String) : Event()
        data class NoSearchData(val text: String) : Event()
    }

}