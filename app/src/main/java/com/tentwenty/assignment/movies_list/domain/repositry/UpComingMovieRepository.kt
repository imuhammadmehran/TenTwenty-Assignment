package com.tentwenty.assignment.movies_list.domain.repositry

import com.tentwenty.assignment.movies_list.data.model.upcoming_movie.UpComingMoviesDTO

interface UpComingMovieRepository {
    suspend fun getUpComingMoviesList(): UpComingMoviesDTO
}