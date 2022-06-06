package com.tentwenty.assignment.movies_list.presentation.watch.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tentwenty.assignment.movies_list.common.Resource
import com.tentwenty.assignment.movies_list.domain.use_case.GetMoviesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val moviesDetailsUseCase: GetMoviesDetailsUseCase) :
    ViewModel() {
    private val _movieDetails = MutableStateFlow(MovieDetailsState())
    val movieDetails: StateFlow<MovieDetailsState> = _movieDetails

    fun getMovieDetails(movieId: String) {
        moviesDetailsUseCase(movieId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _movieDetails.value = MovieDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _movieDetails.value = MovieDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _movieDetails.value = MovieDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}