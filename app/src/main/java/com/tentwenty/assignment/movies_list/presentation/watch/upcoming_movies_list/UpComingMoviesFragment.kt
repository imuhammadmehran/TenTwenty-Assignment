package com.tentwenty.assignment.movies_list.presentation.watch.upcoming_movies_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.tentwenty.assignment.R
import com.tentwenty.assignment.databinding.FragmentUpComingMoviesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpComingMoviesFragment :
    Fragment(R.layout.fragment_up_coming_movies) {

    private var _binding: FragmentUpComingMoviesBinding? = null

    private val binding: FragmentUpComingMoviesBinding
        get() = _binding!!


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
//        val avtion = UpComingMoviesFragmentDirections.actionUpComingMoviesToDashboardFragment()
        binding.text.setOnClickListener {
            Toast.makeText(
                it.context,
                "Hello mehran this is binding click listener",
                Toast.LENGTH_SHORT
            ).show()
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider, SearchView.OnQueryTextListener {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.nav_menu, menu)
                val menuItem: MenuItem = menu.findItem(R.id.app_bar_search)
                val searchView: SearchView = menuItem.actionView as SearchView
                searchView.setOnQueryTextListener(this)
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

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

}