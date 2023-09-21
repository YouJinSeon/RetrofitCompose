package com.teddy.example.domain.usecase

import com.teddy.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(imdbID: String) = movieRepository.getMovieDetail(imdbID)
}