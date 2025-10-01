package com.example.c_miniprojectmap_m6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.c_miniprojectmap_m6.model.MovieModel

class MovieAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies: MutableList<MovieModel> = mutableListOf()
    val swipeToDeleteCallback = SwipeToDeleteCallback()

    fun setData(newMovies: List<MovieModel>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    fun addItem(movie: MovieModel) {
        movies.add(0, movie)
        notifyItemInserted(0)
    }

    fun removeItem(position: Int) {
        if (position in 0 until movies.size) {
            movies.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return MovieViewHolder(view, imageLoader, onClickListener)
    }

    override fun getItemCount() = movies.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(movies[position])
    }

    interface OnClickListener {
        fun onMovieClick(movie: MovieModel)
    }

    inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }
}