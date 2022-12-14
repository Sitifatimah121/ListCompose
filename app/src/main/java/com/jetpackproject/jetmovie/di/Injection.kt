package com.jetpackproject.jetmovie.di

import com.jetpackproject.jetmovie.data.MovieRepository

object Injection {
    fun provideRepository(): MovieRepository{
        return MovieRepository.getInstance()
    }
}