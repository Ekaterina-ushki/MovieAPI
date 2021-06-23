package com.example.movieapi.manager

import android.content.Context
import com.example.movieapi.model.Movie
import com.example.movieapi.model.LikedMovie
import com.example.movieapi.model.db.MovieDao
import com.example.movieapi.model.db.AppDatabase
import com.example.movieapi.model.db.LikedMovieDao
import com.example.movieapi.network.ApiService

class MovieManager(context: Context) {
    private var likedMovieDao: LikedMovieDao? = AppDatabase.createDb(context).likedMovieDao()
    private var movieDao: MovieDao? = AppDatabase.createDb(context).movieDao()

    fun likeMovie(item: Movie) {
        val movie = movieDao?.getMovieById(item.id)
        if (movie != null) {
            val likedMovie = likedMovieDao?.getLikedMovie(item.id)
            if (likedMovie == null) {
                likedMovieDao?.insert(LikedMovie(movie.id))
            } else {
                likedMovieDao?.deleteMovie(likedMovie.id)
            }
        }
    }

    suspend fun downloadMoviesByName(movieName: String): List<Movie> {
        val listResponse = ApiService.instance().getMovieByName(movieName)
        val movies = listResponse.body()?.movieList!!

        for(movie in movies) {
            if (movie.poster_path == null)
                movie.poster_path = ""
        }

        saveMoviesToDb(movies)
        return movies
    }

    fun getMoviesByName(movieName: String): List<Movie>? {
        return movieDao?.getMovieLikeTitle(movieName)
    }

    private fun saveMoviesToDb(movies: List<Movie>) {
        for (movie in movies) {
            val movieList = movieDao?.getMovieLikeTitle(movie.title)
            if (movieList.isNullOrEmpty()) {
                movieDao!!.insert(movie)
            }
        }
    }

    fun getLikedMovies(): List<Movie>? {
        return convertLikedMoviesToMovies(likedMovieDao?.getLikedMovies())
    }

    private fun convertLikedMoviesToMovies(likedMovies: List<LikedMovie>?): List<Movie> {
        val movies = mutableListOf<Movie>()
        if (likedMovies != null) {
            for (movie in likedMovies) {
                movies.add(movieDao!!.getMovieById(movie.movie_id))
            }
        }
        return movies
    }
}