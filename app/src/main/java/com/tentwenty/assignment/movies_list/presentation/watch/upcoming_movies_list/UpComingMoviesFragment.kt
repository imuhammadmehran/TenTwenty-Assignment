package com.tentwenty.assignment.movies_list.presentation.watch.upcoming_movies_list

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tentwenty.assignment.R
import com.tentwenty.assignment.databinding.FragmentUpComingMoviesBinding
import com.tentwenty.assignment.movies_list.common.Constants.TAG

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpComingMoviesFragment :
    Fragment(R.layout.fragment_up_coming_movies) {

    private var _binding: FragmentUpComingMoviesBinding? = null

    private val binding: FragmentUpComingMoviesBinding
        get() = _binding!!

    private val viewModel: UpComingMovieViewModel by viewModels()

    private val upComingMoviesAdapter = UpComingMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpComingMoviesBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val action = UpComingMoviesFragmentDirections.actionUpComingMoviesToDashboardFragment()

        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = upComingMoviesAdapter
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider, SearchView.OnQueryTextListener,
            View.OnClickListener {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.nav_menu, menu)
                val menuItem: MenuItem = menu.findItem(R.id.app_bar_search)
                val searchView: SearchView = menuItem.actionView as SearchView

                searchView.apply {
                    setBackgroundColor(Color.TRANSPARENT)
                    setBackgroundResource(R.drawable.search_view_bg)
                }

                searchView.setOnQueryTextListener(this)
                searchView.setOnSearchClickListener(this)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.app_bar_search -> {

                        return true
                    }
                    else -> {
                        false
                    }
                }
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(requireContext(), "query:$query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onClick(p0: View?) {
                (activity as AppCompatActivity).supportActionBar!!.apply {
                    setDisplayHomeAsUpEnabled(false)
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

//        viewModel.getUpComingMovies()


        lifecycle.coroutineScope.launchWhenCreated {

            Log.d(TAG, "onViewCreated: current thread --> ${Thread.currentThread().name}")

            viewModel.upComingMovies.collect {
                Log.d(TAG, "Collecting movies list flow...")

                if (it.isLoading) {
                    binding.tvNoUpcomingVideoFound.visibility = View.GONE
                    binding.progressUpcomingMovie.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.tvNoUpcomingVideoFound.visibility = View.GONE
                    binding.progressUpcomingMovie.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                Log.d(TAG, "data: ${it.data}")

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.tvNoUpcomingVideoFound.visibility = View.VISIBLE
                    }
                    binding.progressUpcomingMovie.visibility = View.GONE
                    upComingMoviesAdapter.setContentList(it.toMutableList())
                }


            }
        }


        upComingMoviesAdapter.itemClickListener {
            Log.d(TAG, "item Clicked: $it")
        }

    }

}