package com.tentwenty.assignment.movies_list.data.model.upcoming_movie

import com.tentwenty.assignment.movies_list.domain.model.UpComingMovie

data class UpComingMovieDTO(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun UpComingMovieDTO.toDomainUpComingMovie(): UpComingMovie {
    return UpComingMovie(
        id = this.id ?: 0,
        title = this.title ?: "",
        poster_path = this.poster_path ?: ""
    )
}

