package com.rami.koroutinesdemo.domain.di

import com.rami.koroutinesdemo.data.repository.MovieRepository
import com.rami.koroutinesdemo.domain.interactors.MovieInteractor
import com.rami.koroutinesdemo.domain.interactors.MovieInteractorImpl
import com.rami.koroutinesdemo.domain.mappers.MovieMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideMoviesInteractor(repository: MovieRepository, mapper: MovieMapper): MovieInteractor {
        return MovieInteractorImpl(repository, mapper)
    }
}