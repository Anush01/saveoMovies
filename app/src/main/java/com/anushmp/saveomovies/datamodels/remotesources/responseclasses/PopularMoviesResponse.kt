package com.anushmp.saveomovies.datamodels.remotesources.responseclasses

import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse (

    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<Results>,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("total_results") val total_results : Int
)