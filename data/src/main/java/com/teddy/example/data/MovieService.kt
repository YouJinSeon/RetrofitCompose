package com.teddy.example.data

import com.teddy.example.data.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    /**
     * 영화 검색
     */
    @GET("?type=movie")
    suspend fun getSearchResults(
        @Query(value = "s") s: String,
        @Query(value = "apiKey") apiKey: String,
        @Query(value = "page") pageIndex: Int
    ): SearchResult

}