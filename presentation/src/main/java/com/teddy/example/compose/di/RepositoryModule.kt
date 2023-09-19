package com.teddy.example.compose.di

import com.teddy.example.data.MovieService
import com.teddy.example.data.repository.MovieRepositoryImpl
import com.teddy.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideMovieRepository(
        movieService: MovieService
    ): MovieRepository = MovieRepositoryImpl(movieService)
}