package com.tentwenty.assignment.movies_list.domain.use_case

import com.tentwenty.assignment.movies_list.common.Resource
import com.tentwenty.assignment.movies_list.data.model.trailer_video.toDomainTrailerVideo
import com.tentwenty.assignment.movies_list.domain.model.TrailerVideo
import com.tentwenty.assignment.movies_list.domain.repositry.TrailerVideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTrailerVideoUseCase @Inject constructor(private val getTrailerVideoRepository: TrailerVideoRepository) {

    operator fun invoke(movieId: String): Flow<Resource<List<TrailerVideo>>> =
        flow {
            try {
                emit(Resource.Loading())
                val data = getTrailerVideoRepository.getTrailerVideo(movieId)
                val domainData = data.trailerVideoDTOS.map {
                    it.toDomainTrailerVideo()
                }
                emit(Resource.Success(data = domainData))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
            } catch (e: Exception) {

            }
        }
}