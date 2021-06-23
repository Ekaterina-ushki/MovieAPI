package com.example.movieapi.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results") val movieList: List<Movie>
)