package com.tentwenty.assignment.movies_list.domain.repositry

import com.tentwenty.assignment.movies_list.data.model.updoming_movie.UpComingMoviesDTO

interface UpComingMovieRepository {
    suspend fun getUpComingMoviesList(apiKey: String): UpComingMoviesDTO
}