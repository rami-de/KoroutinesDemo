package com.rami.koroutinesdemo.data.repository

import com.rami.koroutinesdemo.domain.model.Movie
import com.rami.koroutinesdemo.domain.model.SimpleMovie

interface MovieRepository {

    suspend fun getPopularMovies(page: Int = 0): List<SimpleMovie>

    suspend fun getSingleMovie(movieId: Int): Movie
}