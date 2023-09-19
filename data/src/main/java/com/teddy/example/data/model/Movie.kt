package com.teddy.example.data.model

/**
 *  movies open API https://www.omdbapi.com/
 *  위 open API data response 값이 파스칼 규칙을 사용한다
 */
data class Movie(
    val Actors: String = "",
    val Awards: String = "",
    val BoxOffice: String? = "",
    val Country: String? = "",
    val DVD: String? = "",
    val Director: String = "",
    val Genre: String? = "",
    val Language: String? = "",
    val Metascore: String? = "",
    val Plot: String? = "",
    val Poster: String? = "",
    val Production: String? = "",
    val Rated: String? = "",
    val Ratings: List<Rating>? = listOf(),
    val Released: String? = "",
    val Response: String? = "",
    val Runtime: String? = "",
    val Title: String? = "",
    val Type: String? = "",
    val Website: String? = "",
    val Writer: String? = "",
    val Year: String? = ""
)
