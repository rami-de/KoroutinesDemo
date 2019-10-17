package com.rami.koroutinesdemo.di

import com.rami.koroutinesdemo.data.di.DataModule
import com.rami.koroutinesdemo.ui.activities.MainActivity
import com.rami.koroutinesdemo.ui.di.UIModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UIModule::class, DataModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}