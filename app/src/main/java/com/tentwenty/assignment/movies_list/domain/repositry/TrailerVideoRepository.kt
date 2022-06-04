package com.tentwenty.assignment.movies_list.domain.repositry

import com.tentwenty.assignment.movies_list.data.model.trailer_video.TrailerVideosDTO

interface TrailerVideoRepository {
    suspend fun getTrailerVideo(movieId: String, apiKey: String): TrailerVideosDTO
}