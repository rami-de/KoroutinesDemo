package com.rami.koroutinesdemo.domain.mappers

import com.rami.koroutinesdemo.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MovieMapperTest {

    val mapper = MovieMapper()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun mapFullMovie_emptyGenresList_shouldReturnMovieWithEmptyGenre() = runBlocking {
        val movie = Movie("Joker", 123, "some url", "some other url",
            "2019", listOf(), "Some movie description", "8.3", "130")

        val mappedMovie = mapper.mapFullMovie(movie)

        assertTrue(mappedMovie.genres.isEmpty())
        return@runBlocking
    }
}