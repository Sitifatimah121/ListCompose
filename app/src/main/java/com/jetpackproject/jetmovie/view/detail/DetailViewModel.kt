package com.jetpackproject.jetmovie.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackproject.jetmovie.data.MovieRepository
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.util.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieRepository
    ) : ViewModel() {

    private val _state: MutableStateFlow<State<Movie>> =
        MutableStateFlow(State.Loading)

    val state: StateFlow<State<Movie>>
    get() = _state

    fun getMovieById(movieId: Int){
        viewModelScope.launch {
            _state.value = State.Loading
            _state.value = State.Success(repository.getMovieById(movieId))
        }
    }
}