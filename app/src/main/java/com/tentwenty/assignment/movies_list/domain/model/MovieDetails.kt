package com.tentwenty.assignment.movies_list.domain.model

import com.tentwenty.assignment.movies_list.data.model.movie_details.Genre

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<Genre>,
    val poster_path: String,
    val release_date: String,
    val video: Boolean
)
