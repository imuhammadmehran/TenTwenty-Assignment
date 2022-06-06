package com.tentwenty.assignment.movies_list.presentation.watch.upcoming_movies_list

import com.tentwenty.assignment.movies_list.domain.model.UpComingMovie

data class UpComingMoviesState(
    val isLoading: Boolean = false,
    val data: List<UpComingMovie>? = null,
    val error: String = ""
)
