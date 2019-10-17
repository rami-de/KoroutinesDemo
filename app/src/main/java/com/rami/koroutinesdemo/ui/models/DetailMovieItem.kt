package com.rami.koroutinesdemo.ui.models

data class DetailMovieItem(
    val title: String,
    val bigPosterUrl: String,
    val smallPosterUrl: String,
    val runTime: String,
    val rating: String,
    val genres: String,
    val releaseYear: String,
    val description: String
)