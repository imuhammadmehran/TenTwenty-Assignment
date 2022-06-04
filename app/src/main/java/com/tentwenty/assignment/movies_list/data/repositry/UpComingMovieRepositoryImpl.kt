package com.tentwenty.assignment.movies_list.data.repositry

import com.tentwenty.assignment.movies_list.data.model.upcoming_movie.UpComingMoviesDTO
import com.tentwenty.assignment.movies_list.data.remote.MoviesApi
import com.tentwenty.assignment.movies_list.domain.repositry.UpComingMovieRepository

class UpComingMovieRepositoryImpl(private val moviesApi: MoviesApi) : UpComingMovieRepository {
    override suspend fun getUpComingMoviesList(apiKey: String): UpComingMoviesDTO {
        return moviesApi.getUpComingMoviesList(apiKey)
    }
}