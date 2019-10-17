package com.rami.koroutinesdemo.ui.di

import android.content.Context
import android.net.Uri
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UIModule {

    val BASE_TMDB_IMAGES_URL = "https://image.tmdb.org/t/p/w185"

    @Provides
    @Singleton
    fun providePicasso(context: Context): Picasso {
        return Picasso.Builder(context)
            .requestTransformer {
                val request = Request.Builder(Uri.parse(BASE_TMDB_IMAGES_URL + it.uri.toString())).build()
                request
            }
            .build()
    }
}