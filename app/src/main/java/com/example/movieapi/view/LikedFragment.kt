package com.example.movieapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapi.databinding.FragmentLikedBinding
import com.example.movieapi.manager.MovieManager
import com.example.movieapi.manager.UiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LikedFragment : Fragment() {
    private var _binding: FragmentLikedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var movieManager: MovieManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLikedBinding.inflate(inflater, container, false)
        val view = binding.root

        movieManager = MovieManager(requireContext())

        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieManager.getLikedMovies()
            withContext(Dispatchers.Main) {
                UiHelper.updateAdapter(view, binding.recView, movies)
            }
        }
        return view
    }
}