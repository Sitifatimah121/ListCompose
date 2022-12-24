package com.jetpackproject.jetmovie.util

sealed class UiState<out T: Any?> {
    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: T) : UiState<T>()

    data class Error(val errorManager: String) : UiState<Nothing>()
}