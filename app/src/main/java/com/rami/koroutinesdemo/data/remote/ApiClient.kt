package com.rami.koroutinesdemo.data.remote

import com.rami.koroutinesdemo.data.remote.dto.MovieDTO
import com.rami.koroutinesdemo.data.remote.dto.MovieListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): MovieListDTO

    @GET("movie/{movieId}")
    suspend fun getSingleMovieById(@Path("movieId") movieId: Int): MovieDTO
}