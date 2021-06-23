package com.example.movieapi.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapi.model.Movie
import com.example.movieapi.model.LikedMovie

@Database(entities = arrayOf(Movie::class, LikedMovie::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun likedMovieDao(): LikedMovieDao

    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "movieapi").build()
    }
}