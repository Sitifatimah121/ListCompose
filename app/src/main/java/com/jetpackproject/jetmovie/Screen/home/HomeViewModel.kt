package com.jetpackproject.jetmovie.Screen.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackproject.jetmovie.data.MovieRepository
import com.jetpackproject.jetmovie.model.Movie
import com.jetpackproject.jetmovie.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _state: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val state = _state.asStateFlow()

    fun getAllMovie(){
        viewModelScope.launch {
            repository.getAllMovie()
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect{ movie ->
                    _state.value = UiState.Success(movie)
                    Log.d(TAG, "getAllMovie: ${movie}")
                }
        }
    }


    private val _query = mutableStateOf("")
    val query: State<String> get () = _query

    fun searchMovie(newQuery: String) = viewModelScope.launch{
        _query.value = newQuery
        repository.searchMovie(_query.value)
            .catch {
                _state.value = UiState.Error(it.message.toString())
            }
            .collect{ movie ->
                _state.value = UiState.Success(movie)
            }
    }
}