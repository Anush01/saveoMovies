package com.anushmp.saveomovies.datamodels.remotesources.responseclasses

import com.google.gson.annotations.SerializedName

data class Spoken_languages (

    @SerializedName("english_name") val english_name : String,
    @SerializedName("iso_639_1") val iso_639_1 : String,
    @SerializedName("name") val name : String
)