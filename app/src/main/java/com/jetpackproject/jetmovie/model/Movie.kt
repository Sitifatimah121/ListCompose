package com.jetpackproject.jetmovie.model

data class Movie (
    val id : Int,
    val title : String,
    val genre : String,
    val photoUrl : String,
    val realease : String,
    val description : String
)