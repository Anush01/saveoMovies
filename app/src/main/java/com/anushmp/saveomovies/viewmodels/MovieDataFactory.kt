package com.anushmp.saveomovies.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushmp.saveomovies.repositories.MovieDataRepository

class MovieDataFactory(val repo: MovieDataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(repo) as T
    }
}