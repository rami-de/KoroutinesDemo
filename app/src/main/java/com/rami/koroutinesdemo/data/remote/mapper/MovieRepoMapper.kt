package com.rami.koroutinesdemo.data.remote.mapper

import com.rami.koroutinesdemo.data.remote.dto.MovieListDTO
import com.rami.koroutinesdemo.data.remote.dto.SimpleMovieDTO
import com.rami.koroutinesdemo.domain.model.SimpleMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepoMapper @Inject constructor() {

    suspend fun mapToMovieList(dto: MovieListDTO): List<SimpleMovie> = withContext(Dispatchers.Default) {
        val list: MutableList<SimpleMovie> = mutableListOf()
        dto.results?.forEach {
            list.add(mapSingleSimpleMovie(it))
        }
        list
    }

    fun mapSingleSimpleMovie(dto: SimpleMovieDTO): SimpleMovie {
        return SimpleMovie(
            dto.title ?: "",
            dto.posterPath ?: "",
            dto.voteAverage.toString(),
            dto.releaseDate?.take(4) ?: "",
            dto.id ?: 0)
    }
}