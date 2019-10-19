package com.rami.koroutinesdemo.domain.model

data class Movie(val title: String,
                 val id: Int,
                 val largePosterUrl: String,
                 val smallPosterUrl: String,
                 val releaseYear: String,
                 val genres: List<String>,
                 val description: String,
                 val rating: String,
                 val runTime: String)