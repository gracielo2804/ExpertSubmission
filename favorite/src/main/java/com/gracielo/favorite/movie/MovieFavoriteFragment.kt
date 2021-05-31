package com.gracielo.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.core.ui.MovieAdapter
import com.gracielo.expertsubmission1.ui.detail.DetailActivity
import com.gracielo.favorite.databinding.FragmentMovieFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentMovieFavoriteBinding
    private val movieFavoriteViewModel: MovieFavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val adapters = MovieAdapter()
            adapters.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.TYPE_FILM, "Movie")
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            movieFavoriteViewModel.favMovie.observe(viewLifecycleOwner, { listMovie ->
                adapters.setData(listMovie)
                binding.tvError.visibility =  if (listMovie.isNotEmpty()) View.GONE else View.VISIBLE

            })
            with(binding.rvMoviesFav) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapters
            }

        }
    }

}