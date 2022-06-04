package com.tentwenty.assignment.movies_list.data.model.trailer_video

import com.tentwenty.assignment.movies_list.domain.model.TrailerVideo

data class TrailerVideoDTO(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val published_at: String,
    val site: String,
    val size: Int,
    val type: String
)

fun TrailerVideoDTO.toDomainTrailerVideo(): TrailerVideo {
    return TrailerVideo(
        id = this.id,
        key = this.key,
        size = this.size,
        name = this.name,
        site = this.site,
        type = this.type
    )
}