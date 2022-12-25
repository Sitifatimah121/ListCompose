package com.jetpackproject.jetmovie.screen.detail

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackproject.jetmovie.data.MovieRepository
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieRepository
    ) : ViewModel() {

    private val _state: MutableStateFlow<UiState<Movie>> =
        MutableStateFlow(UiState.Loading)

    val state = _state.asStateFlow()

    fun getMovieById(movieId: Int) =
        viewModelScope.launch {
            repository.getMovieById(movieId)
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect{ id ->
                    _state.value = UiState.Success(id)
                }
            Log.d(ContentValues.TAG, "getAllMovieDetail: ${movieId}")

        }

}