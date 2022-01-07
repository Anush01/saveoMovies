package com.anushmp.saveomovies.datamodels.remotesources.retrofitutilities

import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.MovieDetailResponse
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    //popular?api_key=328c283cd27bd1877d9080ccb1604c91&sort_by=release_date.desc&language=en-US&page=2
    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apikey: String,
        @Query("sort_by") release_dateDOTdesc: String,
        @Query("language") enDASHUS: String,
        @Query("page") page: Int
    ): Call<PopularMoviesResponse>

    @GET("popular")
    suspend fun getPagedPopularMovies(
        @Query("api_key") apikey: String,
        @Query("sort_by") release_dateDOTdesc: String,
        @Query("language") enDASHUS: String,
        @Query("page") page: Int
    ): PopularMoviesResponse


    ///movie/675445?api_key=328c283cd27bd1877d9080ccb1604c91&language=en-US
    @GET("{id}")
    fun getMovieDetails(
        @Path("id") id: String?,
        @Query("api_key") apikey: String,
        @Query("language") enDASHUS: String
    ): Call<MovieDetailResponse>

    //movie/movie/ was the issue. lmao


}