package com.example.c_miniprojectmap_m6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c_miniprojectmap_m6.model.MovieModel
import com.example.c_miniprojectmap_m6.model.Type

private const val SYNOPSIS_PREVIEW = 70

class MovieViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: MovieAdapter.OnClickListener
) : RecyclerView.ViewHolder(containerView) {
    private val posterView: ImageView by lazy {
        containerView.findViewById(R.id.movie_poster)
    }

    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.movie_title)
    }
    private val typeYearView: TextView by lazy {
        containerView.findViewById(R.id.movie_type_year)
    }

    private val genreView: TextView by lazy {
        containerView.findViewById(R.id.movie_genre)
    }
    private val synopsisView: TextView by lazy {
        containerView.findViewById(R.id.movie_synopsis)
    }

    private val castView: TextView by lazy {
        containerView.findViewById(R.id.movie_cast)
    }

    fun bindData(movie: MovieModel) {
        containerView.setOnClickListener {
            onClickListener.onMovieClick(movie)
        }
        imageLoader.loadImage(movie.imageUrl, posterView)
        titleView.text = movie.title

        val typeLabel = if (movie.type == Type.Movie) "Movie" else "TV Show"
        typeYearView.text = "$typeLabel (${movie.year})"
        genreView.text = "Genre: ${movie.genre.joinToString(", ") {it.displayName}}"

        synopsisView.text = if (movie.synopsis.length > SYNOPSIS_PREVIEW)
            movie.synopsis.take(SYNOPSIS_PREVIEW) + "..." else movie.synopsis

        castView.text = if (movie.cast.isNotEmpty()) {
            "Cast: ${movie.cast.joinToString(", ")}"
        } else {
            ""
        }
    }
}