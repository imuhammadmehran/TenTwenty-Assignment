package com.tentwenty.assignment.movies_list.data.model.updoming_movie

data class UpComingMoviesDTO(
    val dates: Dates,
    val page: Int,
    val upComingMovieDTOS: List<UpComingMovieDTO>,
    val total_pages: Int,
    val total_results: Int
)