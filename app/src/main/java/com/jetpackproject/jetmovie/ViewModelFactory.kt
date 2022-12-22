package com.jetpackproject.jetmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpackproject.jetmovie.data.MovieRepository
import com.jetpackproject.jetmovie.Screen.detail.DetailViewModel
import com.jetpackproject.jetmovie.Screen.home.HomeViewModel

class ViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}