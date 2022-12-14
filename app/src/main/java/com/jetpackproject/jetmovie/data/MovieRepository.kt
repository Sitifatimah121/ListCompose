package com.jetpackproject.jetmovie.data

import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.model.MovieData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow

class MovieRepository {
    private val movie = mutableListOf<Movie>()

    init {
        if (movie.isEmpty()){
            MovieData.movie.forEach {
                movie.add(it)
            }
        }
    }

    fun getAllMovie(): Flow<List<Movie>> {
        return flowOf(movie)
    }

    fun getMovieById(movieId: Int): Movie{
        return movie.first{
            it.id == movieId
        }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this){
                MovieRepository().apply {
                    instance = this
                }
            }
    }
}