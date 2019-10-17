package com.rami.koroutinesdemo.presentation

import androidx.lifecycle.*
import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import com.rami.koroutinesdemo.ui.models.DetailMovieItem
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private var movieId: Int = 0
    private val state = MutableLiveData<DetailScreenState>()

    fun init(movieId: Int) {
        this.movieId = movieId
    }

    fun onResumed() {
        loadMovieDetails()
    }

    fun loadMovieDetails() {
        viewModelScope.launch {
            setState(DetailScreenState.Loading)
            try {
                val movie = interactor.getMovieById(movieId)
                if (movie.bigPosterUrl.isNotEmpty()) {
                    setState(DetailScreenState.BigPosterAvailable(movie.bigPosterUrl))
                }
                if (movie.smallPosterUrl.isNotEmpty()) {
                    setState(DetailScreenState.SmallPosterAvailable(movie.smallPosterUrl))
                }
                setState(DetailScreenState.Ready(movie))
            } catch (e: Exception) {
                setState(DetailScreenState.Error)
            }
        }
    }

    private fun setState(newState: DetailScreenState) {
        if (state.value != newState) {
            state.value = newState
        }
    }

    fun getState(): LiveData<DetailScreenState> {
        return state
    }
}

sealed class DetailScreenState {
    object Loading : DetailScreenState()
    object Error : DetailScreenState()
    data class BigPosterAvailable(val url: String) : DetailScreenState()
    data class SmallPosterAvailable(val url: String) : DetailScreenState()
    data class Ready(val movie: DetailMovieItem) : DetailScreenState()
}