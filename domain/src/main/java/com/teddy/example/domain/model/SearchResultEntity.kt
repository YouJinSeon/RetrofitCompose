package com.teddy.example.domain.model

data class SearchResultEntity(
    val search: List<SearchEntity>?,
    val totalResult: String?
)
