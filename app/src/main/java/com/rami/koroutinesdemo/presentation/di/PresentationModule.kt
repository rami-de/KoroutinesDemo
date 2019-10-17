package com.rami.koroutinesdemo.presentation.di

import androidx.lifecycle.ViewModel
import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import com.rami.koroutinesdemo.presentation.MovieDetailViewModel
import com.rami.koroutinesdemo.presentation.MovieListPresenter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    fun provideMovieListPresenter(interactor: MovieInteractor): MovieListPresenter {
        return MovieListPresenter(interactor)
    }

    @Provides
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun provideMovieDetailViewModel(interactor: MovieInteractor): ViewModel {
        return MovieDetailViewModel(interactor)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }
}