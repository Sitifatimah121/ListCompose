package com.jetpackproject.jetmovie.Screen.home

import android.content.ContentValues.TAG
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

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _state: MutableStateFlow<State<List<Movie>>> = MutableStateFlow(State.Loading)
    val state = _state.asStateFlow()

    fun getAllMovie(){
        viewModelScope.launch {
            repository.getAllMovie()
                .catch {
                    _state.value = State.Error(it.message.toString())
                }
                .collect{ movie ->
                    _state.value = State.Success(movie)
                    Log.d(TAG, "getAllMovie: ${movie}")
                }
        }
    }
}