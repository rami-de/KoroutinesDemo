package com.rami.koroutinesdemo.data.repository

import com.rami.koroutinesdemo.data.remote.ApiClient
import com.rami.koroutinesdemo.data.remote.mapper.MovieRepoMapper
import com.rami.koroutinesdemo.domain.model.Movie
import com.rami.koroutinesdemo.domain.model.SimpleMovie

class MovieRepositoryImpl(private val apiClient: ApiClient, private val mapper: MovieRepoMapper) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<SimpleMovie> {
        val response = apiClient.getPopularMovies()
        return mapper.mapToMovieList(response)
    }

    override suspend fun getSingleMovie(): Movie {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}