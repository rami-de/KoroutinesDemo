package com.rami.koroutinesdemo.data.remote.mapper

import com.rami.koroutinesdemo.data.remote.dto.MovieDTO
import com.rami.koroutinesdemo.data.remote.dto.MovieListDTO
import com.rami.koroutinesdemo.data.remote.dto.SimpleMovieDTO
import com.rami.koroutinesdemo.domain.model.Movie
import com.rami.koroutinesdemo.domain.model.SimpleMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MovieRepoMapper @Inject constructor() {

    suspend fun mapToMovieList(dto: MovieListDTO?): List<SimpleMovie> = withContext(Dispatchers.Default) {
        val list: MutableList<SimpleMovie> = mutableListOf()
        dto?.results?.forEach {
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

    suspend fun mapToMovie(dto: MovieDTO?): Movie = withContext(Dispatchers.Default) {
        if (dto == null) {
            throw Exception()
        }
        var genreString = ""
        dto.genres?.forEach {
            genreString += it.name
        }
        Movie(dto.title ?: "", dto.id ?: 0, dto.backdropPath ?: "",
            dto.posterPath ?: "", dto.releaseDate?.take(4) ?: "Unknown year",
            genreString, dto.overview ?: "", "${dto.voteAverage}", dto.runtime.toString())
    }
}