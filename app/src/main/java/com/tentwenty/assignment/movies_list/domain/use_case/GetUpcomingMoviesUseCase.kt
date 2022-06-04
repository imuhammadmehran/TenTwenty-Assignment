package com.tentwenty.assignment.movies_list.domain.use_case

import com.tentwenty.assignment.movies_list.common.Resource
import com.tentwenty.assignment.movies_list.data.model.upcoming_movie.toDomainUpComingMovie
import com.tentwenty.assignment.movies_list.domain.model.UpComingMovie
import com.tentwenty.assignment.movies_list.domain.repositry.UpComingMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val upComingMovieRepository: UpComingMovieRepository) {


    operator fun invoke(apiKey: String): Flow<Resource<List<UpComingMovie>>> = flow {
        try {
            emit(Resource.Loading())
            val data = upComingMovieRepository.getUpComingMoviesList(apiKey)
            val domainData =
                data.upComingMovieDTOS.map {
                    it.toDomainUpComingMovie()
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