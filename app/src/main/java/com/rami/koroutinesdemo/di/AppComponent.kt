package com.rami.koroutinesdemo.di

import com.rami.koroutinesdemo.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}