package com.anushmp.saveomovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.MovieDetailResponse
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.repositories.MovieDataRepository

class MoviesViewModel(val repo: MovieDataRepository):ViewModel(){

    var responseList = repo.responseList
    var responseListLiveData = MutableLiveData<List<Results?>>()
    var pageNumber: Int = 1


    var movieDetailResponse = repo.movieDetailResponse
    var movieDetailLiveData = MutableLiveData<MovieDetailResponse?>()


    fun getPopularMovesAsLiveData(): LiveData<PagingData<Results>>{

        return repo.getPagedListOfCharacters()

    }



    fun returnMoviesLiveData(): MutableLiveData<List<Results?>>{

        return responseListLiveData

    }

    fun returnMovieDetailLiveData(): MutableLiveData<MovieDetailResponse?>{

        return movieDetailLiveData

    }

    fun repoGetPopularMovies(){

        repo.RepoGetPopularMovies(pageNumber,responseListLiveData)

    }

    fun repoGetThisMoviesDetails(movieId:String?){

        repo.RepoGetMovieDetails(movieId, movieDetailLiveData)

    }

    fun returnMovieDetailResponse(): MovieDetailResponse?{

        return repo.movieDetailResponse

    }





}