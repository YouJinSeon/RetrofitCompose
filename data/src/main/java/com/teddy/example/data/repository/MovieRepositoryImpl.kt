package com.teddy.example.data.repository

import com.teddy.example.data.MovieService
import com.teddy.example.domain.model.MovieEntity
import com.teddy.example.domain.model.RatingEntity
import com.teddy.example.domain.model.SearchEntity
import com.teddy.example.domain.model.SearchResultEntity
import com.teddy.example.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MovieRepositoryImpl @Inject constructor(
  private val movieService: MovieService
) : MovieRepository {
    override suspend fun getSearchResults(title: String): Flow<SearchResultEntity?> {
        return flow {
            val temp = movieService.getSearchResults(title, "eea09d0c" , 1)
            val data = SearchResultEntity(
                temp.Search?.map {
                    SearchEntity(
                        it.Title,
                        it.Year ?: "",
                        it.Poster,
                        it.imdbID
                    )
                },
                temp.totalResults
            )
            emit(data) // 데이터 방출
        }
    }

    override suspend fun getMovieDetail(imdbID: String): Flow<MovieEntity?> {
        return flow {
            val temp = movieService.getMovieDetails("eea09d0c", imdbID)
            val data = MovieEntity(
                Title = temp.Title,
                Actors = temp.Actors,
                Country = temp.Country,
                Director = temp.Director,
                Poster = temp.Poster,
                Genre = temp.Genre,
                Rated = temp.Rated,
                Plot = temp.Plot,
                Awards = temp.Awards,
                Ratings = temp.Ratings?.map { RatingEntity(it.Source, it.Value) },
                Writer = temp.Writer,
                Year = temp.Year
            )
            emit(data)
        }
    }
}