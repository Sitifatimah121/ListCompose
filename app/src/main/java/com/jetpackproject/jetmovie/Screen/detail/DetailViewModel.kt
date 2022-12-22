package com.jetpackproject.jetmovie.Screen.detail

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackproject.jetmovie.data.MovieRepository
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.util.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieRepository
    ) : ViewModel() {

    private val _state: MutableStateFlow<State<Movie>> =
        MutableStateFlow(State.Loading)

    val state = _state.asStateFlow()

    fun getMovieById(movieId: Int) =
        viewModelScope.launch {
            repository.getMovieById(movieId)
                .catch {
                    _state.value = State.Error(it.message.toString())
                }
                .collect{ id ->
                    _state.value = State.Success(id)
                }
            Log.d(ContentValues.TAG, "getAllMovieDetail: ${movieId}")

        }

}