package com.teddy.example.domain.usecase

import com.teddy.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(title: String) = movieRepository.getSearchResults(title)
}