package com.rami.koroutinesdemo.presentation

import com.rami.koroutinesdemo.ui.models.SimpleMovieItem

interface MovieListView {

    fun showLoader()

    fun hideLoader()

    fun updateData(movies: List<SimpleMovieItem>)

    fun hideMovies()

    fun showMovies()

    fun navigateToMovieDetails(movieId: Int)

    fun disableListClicks()

    fun enableListClicks()

    fun showError()

    fun hideError()
}