package com.rami.koroutinesdemo.di

import com.rami.koroutinesdemo.data.di.DataModule
import com.rami.koroutinesdemo.domain.di.DomainModule
import com.rami.koroutinesdemo.presentation.di.PresentationModule
import com.rami.koroutinesdemo.ui.activities.MovieDetailActivity
import com.rami.koroutinesdemo.ui.activities.MovieListActivity
import com.rami.koroutinesdemo.ui.di.UIModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UIModule::class, DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {

    fun inject(activity: MovieListActivity)

    fun inject(activity: MovieDetailActivity)
}