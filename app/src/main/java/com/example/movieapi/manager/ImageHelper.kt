package com.example.movieapi.manager

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.model.Movie
import com.example.movieapi.view.MovieAdapter

class ImageHelper {
    companion object {

        fun getImageUrl(imageUrl: String) : String {
            return "https://image.tmdb.org/t/p/w500$imageUrl"
        }
    }
}