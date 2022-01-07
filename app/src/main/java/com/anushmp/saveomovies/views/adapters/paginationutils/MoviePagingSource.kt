package com.anushmp.saveomovies.views.adapters.paginationutils

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.PopularMoviesResponse
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.datamodels.remotesources.retrofitutilities.RetrofitNetworker

class MoviePagingSource: PagingSource<Int,Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {

        val pageNumber = params.key ?: 1

        val api_Key = "328c283cd27bd1877d9080ccb1604c91"

        val popularMoviesResponse = RetrofitNetworker
            .getApiServiceInstance()
            .getPagedPopularMovies(api_Key,
                "release_date.desc",
                "en-US",
                pageNumber)

        val movieList = popularMoviesResponse.results

        return try {
            LoadResult.Page(
                data = movieList,
                prevKey = null,
                nextKey = if (movieList.isEmpty())null else pageNumber + 1
            )

        }catch (e: Exception){

            LoadResult.Error(e)

        }

    }
}