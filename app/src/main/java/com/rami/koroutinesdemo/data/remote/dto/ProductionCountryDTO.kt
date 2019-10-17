package com.rami.koroutinesdemo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductionCountryDTO (

    @SerializedName("iso_3166_1") val iso_3166_1 : String?,
    @SerializedName("name") val name : String?
)