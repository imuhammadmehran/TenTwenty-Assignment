package com.tentwenty.assignment.movies_list.data.repositry

import com.tentwenty.assignment.movies_list.data.model.trailer_video.TrailerVideosDTO
import com.tentwenty.assignment.movies_list.data.remote.MoviesApi
import com.tentwenty.assignment.movies_list.domain.repositry.TrailerVideoRepository

class TrailerVideoRespositoryImpl(private val moviesApi: MoviesApi) : TrailerVideoRepository {
    override suspend fun getTrailerVideo(movieId: String, apiKey: String): TrailerVideosDTO {
        return moviesApi.getTrailerVideo(movieId, apiKey)
    }

}