package com.rami.koroutinesdemo.presentation

import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListPresenter(private val interactor: MovieInteractor) {

    private var view: MovieListView? = null
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun setView(view: MovieListView) {
        this.view = view
    }

    fun onResumed() {
        view?.enableListClicks()
        loadMovies()
    }

    fun loadMovies() {
        view?.hideMovies()
        view?.showLoader()
        scope.launch {
            try {
                val movies = interactor.getPopularMovies()
                if (movies.isNotEmpty()) {
                    view?.hideLoader()
                    view?.updateData(movies)
                    view?.showMovies()
                }
            } catch (e: Exception) {
                view?.showError()
            }
        }
    }

    fun onMovieItemClicked(movieId: Int) {
        view?.disableListClicks()
        view?.navigateToMovieDetails(movieId)
    }
}