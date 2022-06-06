package com.tentwenty.assignment.movies_list.presentation.watch.upcoming_movies_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tentwenty.assignment.movies_list.domain.model.UpComingMovie
import com.tentwenty.assignment.databinding.UpcomingMovieItemBinding
import com.tentwenty.assignment.movies_list.common.Constants

class UpComingMoviesAdapter : RecyclerView.Adapter<UpComingMoviesAdapter.MyViewHolder>() {


    private var listener: ((UpComingMovie) -> Unit)? = null

    var list = mutableListOf<UpComingMovie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setContentList(list: MutableList<UpComingMovie>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: UpcomingMovieItemBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding =
            UpcomingMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l: (UpComingMovie) -> Unit) {
        listener = l
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val upComingMovie = this.list[position]


       /* holder.viewHolder.upComingMovie = this.list[position].apply {
            this.poster_path = "${Constants.IMAGE_URL}${this.poster_path}"
        }*/

        holder.viewHolder.upComingMovie = UpComingMovie(
            upComingMovie.id,
            upComingMovie.title,
            "${Constants.IMAGE_URL}${upComingMovie.poster_path}"
        )

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}