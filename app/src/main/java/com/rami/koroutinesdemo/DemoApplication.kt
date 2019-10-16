package com.rami.koroutinesdemo

import android.app.Application
import com.rami.koroutinesdemo.di.AppComponent
import com.rami.koroutinesdemo.di.AppModule
import com.rami.koroutinesdemo.di.DaggerAppComponent

class DemoApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}