package com.rami.koroutinesdemo.domain.interactors

import com.rami.koroutinesdemo.data.repository.MovieRepository
import com.rami.koroutinesdemo.domain.mappers.MovieMapper
import com.rami.koroutinesdemo.domain.model.Movie
import com.rami.koroutinesdemo.ui.models.SimpleMovieItem

class MovieInteractorImpl(private val repository: MovieRepository, private val mapper: MovieMapper) : MovieInteractor {

    override suspend fun getPopularMovies(): List<SimpleMovieItem> {
        val movies = repository.getPopularMovies()
        return mapper.mapMovieList(movies)
    }

    override suspend fun getMovieById(movieId: Int): Movie {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}