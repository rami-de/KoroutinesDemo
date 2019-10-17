package com.rami.koroutinesdemo.data.di

import com.rami.koroutinesdemo.data.remote.ApiClient
import com.rami.koroutinesdemo.data.remote.mapper.MovieRepoMapper
import com.rami.koroutinesdemo.data.repository.MovieRepository
import com.rami.koroutinesdemo.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    val TMDB_BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(TMDB_BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("tmdb_api_key") apiKey: String): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor {
                var request = it.request()
                val url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build()
                request = request.newBuilder().url(url).build()
                it.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiClient: ApiClient, mapper: MovieRepoMapper): MovieRepository {
        return MovieRepositoryImpl(apiClient, mapper)
    }
}