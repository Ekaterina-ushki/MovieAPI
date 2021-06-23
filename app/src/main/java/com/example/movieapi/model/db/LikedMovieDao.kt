package com.example.movieapi.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapi.model.LikedMovie;


@Dao
interface LikedMovieDao {
    @Insert
    fun insert(likedMovie: LikedMovie)

    @Query("DELETE FROM liked_movie")
    fun deleteAll()

    @Query("DELETE FROM liked_movie WHERE id = :id")
    fun deleteMovie(id : Int)

    @Query("SELECT * FROM liked_movie")
    fun getLikedMovies() : List<LikedMovie>

    @Query("SELECT * FROM liked_movie WHERE movie_id = :id LIMIT 1")
    fun getLikedMovie(id: Int) : LikedMovie
}
