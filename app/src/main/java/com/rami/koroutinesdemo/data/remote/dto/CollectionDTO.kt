package com.rami.koroutinesdemo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CollectionDTO (
    @SerializedName("id") val id : Int?,
    @SerializedName("name") val name : String?,
    @SerializedName("poster_path") val posterPath : String?,
    @SerializedName("backdrop_path") val backdropPath : String?
)