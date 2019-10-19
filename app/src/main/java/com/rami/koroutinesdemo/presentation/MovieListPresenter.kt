package com.rami.koroutinesdemo.presentation

import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MovieListPresenter(private val interactor: MovieInteractor) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var view: MovieListView? = null
    private var job = Job()

    fun setView(view: MovieListView) {
        this.view = view
    }

    fun onResumed() {
        view?.enableListClicks()
        loadMovies()
    }

    fun loadMovies() {
        view?.hideMovies()
        view?.hideError()
        view?.showLoader()
        launch {
            try {
                val movies = interactor.getPopularMovies()
                if (movies.isNotEmpty()) {
                    view?.hideLoader()
                    view?.updateData(movies)
                    view?.showMovies()
                }
            } catch (e: Exception) {
                view?.hideLoader()
                view?.hideMovies()
                view?.showError()
            }
        }
    }

    fun onMovieItemClicked(movieId: Int) {
        view?.disableListClicks()
        view?.navigateToMovieDetails(movieId)
    }
}