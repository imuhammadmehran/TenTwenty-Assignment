package com.tentwenty.assignment.movies_list.presentation.watch.movie_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs
import com.tentwenty.assignment.R
import com.tentwenty.assignment.databinding.FragmentMovieDetailsBinding
import com.tentwenty.assignment.movies_list.common.Constants
import com.tentwenty.assignment.movies_list.common.Constants.TAG
import com.tentwenty.assignment.movies_list.domain.model.MovieDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment :
    Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null

    private val binding: FragmentMovieDetailsBinding
        get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.movieId?.let {
            viewModel.getMovieDetails(it)
        }
        lifecycle.coroutineScope.launchWhenCreated {

            Log.d(TAG, "onViewCreated: current thread --> ${Thread.currentThread().name}")

            viewModel.movieDetails.collect {
                if (it.isLoading) {
                    Log.d(TAG, "onViewCreated: Loading...")
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {
                    binding.movieDetails = MovieDetails(
                        it.id,
                        it.title,
                        it.overview,
                        it.genres,
                        "${Constants.IMAGE_URL}${it.poster_path}",
                        it.release_date,
                        it.video
                    )
                }

            }
        }

    }

}