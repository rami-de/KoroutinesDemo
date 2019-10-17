package com.rami.koroutinesdemo.domain.interactors

import com.rami.koroutinesdemo.domain.model.Movie
import com.rami.koroutinesdemo.ui.models.SimpleMovieItem

interface MovieInteractor {

    suspend fun getPopularMovies(): List<SimpleMovieItem>

    suspend fun getMovieById(movieId: Int): Movie
}