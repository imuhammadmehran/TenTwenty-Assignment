package com.tentwenty.assignment.movies_list.data.model.upcoming_movie

data class UpComingMoviesDTO(
    val dates: Dates?,
    val page: Int?,
    val results: List<UpComingMovieDTO>?,
    val total_pages: Int?,
    val total_results: Int?
)