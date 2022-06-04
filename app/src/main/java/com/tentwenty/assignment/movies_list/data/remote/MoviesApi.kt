package com.tentwenty.assignment.movies_list.data.remote

import com.tentwenty.assignment.movies_list.data.model.movie_details.MovieDetailsDTO
import com.tentwenty.assignment.movies_list.data.model.trailer_video.TrailerVideosDTO
import com.tentwenty.assignment.movies_list.data.model.upcoming_movie.UpComingMoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("/movie/upcoming/")
    suspend fun getUpComingMoviesList(@Query("api_key") apiKey: String): UpComingMoviesDTO

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetailsDTO

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailerVideo(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): TrailerVideosDTO

}