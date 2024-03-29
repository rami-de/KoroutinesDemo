package com.rami.koroutinesdemo.ui.models

data class SimpleMovieItem(val title: String = "Some movie",
                           val posterUrl: String = "Some url",
                           val rating: String = "8.3",
                           val releaseYear: String = "2018",
                           val id: Int = 0)