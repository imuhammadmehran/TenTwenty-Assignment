package com.tentwenty.assignment.movies_list.presentation.watch.upcoming_movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tentwenty.assignment.movies_list.common.Resource
import com.tentwenty.assignment.movies_list.domain.use_case.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UpComingMovieViewModel @Inject constructor(private val upcomingMoviesUseCase: GetUpcomingMoviesUseCase) :
    ViewModel() {

    init {
        getUpComingMovies()
    }

    private val _upComingMovies = MutableStateFlow(UpComingMoviesState())
    val upComingMovies: StateFlow<UpComingMoviesState> = _upComingMovies

    private fun getUpComingMovies() {
        upcomingMoviesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _upComingMovies.value = UpComingMoviesState(isLoading = true)
                }
                is Resource.Error -> {
                    _upComingMovies.value = UpComingMoviesState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _upComingMovies.value = UpComingMoviesState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}