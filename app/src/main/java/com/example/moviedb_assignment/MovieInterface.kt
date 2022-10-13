package com.example.moviedb_assignment

import retrofit2.Call
import retrofit2.http.GET

interface MovieInterface {
    @GET("/3/movie/popular")
    fun getMovies() : Call<List<Movie>>
}