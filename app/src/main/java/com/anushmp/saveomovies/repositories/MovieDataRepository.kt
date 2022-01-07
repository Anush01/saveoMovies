package com.anushmp.saveomovies.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.MovieDetailResponse
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.PopularMoviesResponse
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.datamodels.remotesources.retrofitutilities.MoviesApi
import com.anushmp.saveomovies.datamodels.remotesources.retrofitutilities.RetrofitNetworker
import com.anushmp.saveomovies.views.adapters.paginationutils.MoviePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataRepository {

    val api_Key = "328c283cd27bd1877d9080ccb1604c91"

    var Retrofit = RetrofitNetworker.getRetrofitInstance()
    var api = Retrofit.create(MoviesApi::class.java)

    var responseList:ArrayList<Results?> = ArrayList()
    var movieDetailResponse: MovieDetailResponse? = null


    fun getPagedListOfCharacters() = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 3,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
        ),
        pagingSourceFactory = {MoviePagingSource()}
    ).liveData


    fun RepoGetPopularMovies(page:Int, ld:MutableLiveData<List<Results?>> ){



         api.getPopularMovies(api_Key,
               "release_date.desc",
               "en-US",
               page).enqueue(
             object : Callback<PopularMoviesResponse>{
                 override fun onResponse(
                 call: Call<PopularMoviesResponse>,
                 response: Response<PopularMoviesResponse>
             ) {
                     var repoResponse = response.body()


                     if(repoResponse != null){

                     responseList.addAll(repoResponse.results)

                 }

                 ld.postValue(responseList)

             }

             override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                 Log.d("MovieDataRepositoryLogs",
                     "Failed to get movies OnFailure Triggered")
             }

         })




    }

    fun RepoGetMovieDetails(MovieId:String?,
                            moviedetailLiveData: MutableLiveData<MovieDetailResponse?>){

        Log.d("AnushLogsQ",MovieId.toString())

        api.getMovieDetails(MovieId,
        api_Key,
        "en-US").enqueue(
            object: Callback<MovieDetailResponse>{
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {

                movieDetailResponse = response.body()

                Log.d("AnushLogsFromOnResponse",response.body().toString())

                response.body()?.let { Log.d("AnushLogsFromOnResponse", it.title) }


                moviedetailLiveData.postValue(movieDetailResponse)
            }


            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.d("MovieDataRepositoryLogs",
                    "Failed to get movies OnFailure Triggered")
            }



        })

    }

}