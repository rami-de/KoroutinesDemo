package com.rami.koroutinesdemo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SpokenLanguageDTO (
    @SerializedName("iso_639_1") val iso_639_1 : String?,
    @SerializedName("name") val name : String?
)