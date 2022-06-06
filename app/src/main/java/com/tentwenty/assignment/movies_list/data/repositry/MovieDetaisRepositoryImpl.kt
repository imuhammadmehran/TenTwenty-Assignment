package com.tentwenty.assignment.movies_list.data.repositry

import com.tentwenty.assignment.movies_list.data.model.movie_details.MovieDetailsDTO
import com.tentwenty.assignment.movies_list.data.remote.MoviesApi
import com.tentwenty.assignment.movies_list.domain.repositry.MovieDetailsRepository

class MovieDetaisRepositoryImpl(private val moviesApi: MoviesApi) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: String): MovieDetailsDTO {
        return moviesApi.getMovieDetails(movieId)
    }
}