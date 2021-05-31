package com.gracielo.expertsubmission1.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.core.ui.MovieAdapter
import com.gracielo.expertsubmission1.databinding.FragmentMovieBinding
import com.gracielo.expertsubmission1.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val movieViewModel: MovieViewModel by viewModel()


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
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
            movieViewModel.movie.observe(viewLifecycleOwner, { listMovie ->
                if (listMovie != null) {
                    when (listMovie) {
                        is com.gracielo.core.data.Resource.Loading -> {
                            Log.d("fragment", "Loading")
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is com.gracielo.core.data.Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapters.setData(listMovie.data)
                        }

                        is com.gracielo.core.data.Resource.Error -> {
                            Log.d("fragment", "Error")
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                listMovie.message ?: "Oops Something Wrong..."
                        }

                    }
                }

            })
            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapters
            }

        }
    }
}