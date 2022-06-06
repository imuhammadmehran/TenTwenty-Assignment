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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    @Singleton
    fun provideMoviesApi(okHttpClient: OkHttpClient): MoviesApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MoviesApi::class.java)
    }


    @Singleton
    @Provides
    fun providesOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: Interceptor,
    ) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesApiKeyInterceptor(): Interceptor = ApiInterceptor()


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

