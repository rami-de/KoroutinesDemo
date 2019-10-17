package com.rami.koroutinesdemo.domain.interactors

import com.rami.koroutinesdemo.ui.models.SimpleMovieItem

interface MovieInteractor {

    suspend fun getPopularMovies(): List<SimpleMovieItem>
}