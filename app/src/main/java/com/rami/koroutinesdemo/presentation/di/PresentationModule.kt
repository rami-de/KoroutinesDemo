package com.rami.koroutinesdemo.presentation.di

import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import com.rami.koroutinesdemo.presentation.MovieListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Provides
    @Singleton
    fun provideMovieListPresenter(interactor: MovieInteractor): MovieListPresenter {
        return MovieListPresenter(interactor)
    }
}