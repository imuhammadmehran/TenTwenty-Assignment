package com.tentwenty.assignment.movies_list.hilt

import com.tentwenty.assignment.movies_list.common.Constants
import com.tentwenty.assignment.movies_list.data.remote.MoviesApi
import com.tentwenty.assignment.movies_list.data.repositry.MovieDetaisRepositoryImpl
import com.tentwenty.assignment.movies_list.data.repositry.TrailerVideoRespositoryImpl
import com.tentwenty.assignment.movies_list.data.repositry.UpComingMovieRepositoryImpl
import com.tentwenty.assignment.movies_list.domain.repositry.MovieDetailsRepository
import com.tentwenty.assignment.movies_list.domain.repositry.TrailerVideoRepository
import com.tentwenty.assignment.movies_list.domain.repositry.UpComingMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MoviesApi::class.java)
    }


    @Provides
    fun provideMovieDetailsRepository(moviesApi: MoviesApi): MovieDetailsRepository {
        return MovieDetaisRepositoryImpl(moviesApi)
    }

    @Provides
    fun provideTrailerVideoRepository(moviesApi: MoviesApi): TrailerVideoRepository {
        return TrailerVideoRespositoryImpl(moviesApi)
    }


    @Provides
    fun provideUpComingMovieRepository(moviesApi: MoviesApi): UpComingMovieRepository {
        return UpComingMovieRepositoryImpl(moviesApi)
    }
}

