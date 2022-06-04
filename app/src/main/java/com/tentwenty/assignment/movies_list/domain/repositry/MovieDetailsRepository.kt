package com.tentwenty.assignment.movies_list.domain.repositry

import com.tentwenty.assignment.movies_list.data.model.movie_details.MovieDetailsDTO

interface MovieDetailsRepository {
    suspend fun getMovieDetails(
        movieId: String,
        apiKey: String
    ): MovieDetailsDTO
}