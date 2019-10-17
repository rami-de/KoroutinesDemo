package com.rami.koroutinesdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rami.koroutinesdemo.R
import com.rami.koroutinesdemo.ui.customviews.MovieCardView
import com.rami.koroutinesdemo.ui.models.MovieItem
import javax.inject.Inject

class MovieListAdapter @Inject constructor(): RecyclerView.Adapter<MovieListAdapter.MovieCardViewHolder>() {

    var movies: List<MovieItem> = listOf(MovieItem(), MovieItem(), MovieItem())

    fun updateItems(movies: List<MovieItem>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item_layout, parent, false) as MovieCardView
        return MovieCardViewHolder(card)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    inner class MovieCardViewHolder(private val card: MovieCardView) : RecyclerView.ViewHolder(card) {

        fun bindItem(movie: MovieItem) {
            card.title.text = movie.title
            card.year.text = movie.releaseYear
            card.rating.text = movie.rating
            // load image with Glide
        }
    }
}