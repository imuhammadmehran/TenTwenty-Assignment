package com.tentwenty.assignment.movies_list.domain.use_case

import com.tentwenty.assignment.movies_list.common.Resource
import com.tentwenty.assignment.movies_list.data.model.movie_details.toDomainMovieDetails
import com.tentwenty.assignment.movies_list.domain.model.MovieDetails
import com.tentwenty.assignment.movies_list.domain.repositry.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val movieDetailsRepository: MovieDetailsRepository) {

    operator fun invoke(movieId: String): Flow<Resource<MovieDetails>> =
        flow {
            try {
                emit(Resource.Loading())
                val data = movieDetailsRepository.getMovieDetails(movieId)
                val domainData = data.toDomainMovieDetails()
                emit(Resource.Success(data = domainData))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
            } catch (e: Exception) {

            }
        }
}