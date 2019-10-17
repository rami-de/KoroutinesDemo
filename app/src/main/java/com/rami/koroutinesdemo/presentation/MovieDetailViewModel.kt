package com.rami.koroutinesdemo.presentation

import androidx.lifecycle.*
import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import com.rami.koroutinesdemo.ui.models.DetailMovieItem
import kotlinx.coroutines.launch

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
            interactor.getMovieById(movieId)
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