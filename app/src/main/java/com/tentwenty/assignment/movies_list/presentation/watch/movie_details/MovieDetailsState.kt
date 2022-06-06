package com.tentwenty.assignment.movies_list.presentation.watch.movie_details

import com.tentwenty.assignment.movies_list.domain.model.MovieDetails


data class MovieDetailsState(
    val isLoading: Boolean = false,
    val data: MovieDetails? = null,
    val error: String = ""
)