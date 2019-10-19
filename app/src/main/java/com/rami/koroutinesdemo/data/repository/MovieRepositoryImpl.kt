package com.rami.koroutinesdemo.data.repository

import com.rami.koroutinesdemo.data.remote.ApiClient
import com.rami.koroutinesdemo.data.remote.mapper.MovieRepoMapper
import com.rami.koroutinesdemo.domain.model.Movie
import com.rami.koroutinesdemo.domain.model.SimpleMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val apiClient: ApiClient, private val mapper: MovieRepoMapper) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<SimpleMovie> = withContext(Dispatchers.IO) {
        val response = apiClient.getPopularMovies()
        mapper.mapToMovieList(response)
    }

    override suspend fun getSingleMovie(movieId: Int): Movie = withContext(Dispatchers.IO) {
        val response = apiClient.getSingleMovieById(movieId)
        mapper.mapToMovie(response)
    }
}