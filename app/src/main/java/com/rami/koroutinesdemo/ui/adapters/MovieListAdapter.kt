package com.rami.koroutinesdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rami.koroutinesdemo.R
import com.rami.koroutinesdemo.ui.customviews.MovieCardView
import com.rami.koroutinesdemo.ui.models.SimpleMovieItem
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieListAdapter @Inject constructor(private val picasso: Picasso) : RecyclerView.Adapter<MovieListAdapter.MovieCardViewHolder>() {

    var movies: List<SimpleMovieItem> = listOf()

    var listener: MovieListNavigationListener? = null

    var isClickable = true

    fun updateItems(movies: List<SimpleMovieItem>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item_layout, parent, false) as MovieCardView
        return MovieCardViewHolder(card, listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    inner class MovieCardViewHolder(private val card: MovieCardView,
                                    private val listener: MovieListNavigationListener?) : RecyclerView.ViewHolder(card) {

        fun bindItem(movie: SimpleMovieItem) {
            card.title.text = movie.title
            card.year.text = card.context.getString(R.string.release_year, movie.releaseYear)
            card.rating.text = movie.rating
            picasso.load(movie.posterUrl)
                .placeholder(R.drawable.ic_popcorn_svgrepo_com)
                .into(card.poster)
            card.setOnClickListener {
                if (isClickable) {
                    listener?.onMovieItemPressed(movie.id)
                }
            }
        }
    }
}

interface MovieListNavigationListener {

    fun onMovieItemPressed(movieId: Int)
}