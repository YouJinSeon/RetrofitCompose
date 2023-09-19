package com.teddy.example.domain.repository

import com.teddy.example.domain.model.SearchResultEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getSearchResults(title: String): Flow<SearchResultEntity?>
}