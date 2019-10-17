package com.rami.koroutinesdemo.domain.mappers

import com.rami.koroutinesdemo.domain.model.SimpleMovie
import com.rami.koroutinesdemo.ui.models.SimpleMovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    suspend fun mapMovieList(movies: List<SimpleMovie>): List<SimpleMovieItem> = withContext(Dispatchers.Default) {
        val mappedMovies = mutableListOf<SimpleMovieItem>()
        movies.forEach {
            mappedMovies.add(mapSingleMovie(it))
        }
        mappedMovies
    }

    fun mapSingleMovie(movie: SimpleMovie): SimpleMovieItem {
        return SimpleMovieItem(movie.title, movie.posterUrl, movie.rating, movie.releaseYear, movie.id)
    }
}