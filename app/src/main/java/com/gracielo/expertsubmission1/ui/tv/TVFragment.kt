package com.gracielo.expertsubmission1.ui.tv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.core.ui.TVAdapter
import com.gracielo.expertsubmission1.databinding.FragmentMovieBinding
import com.gracielo.expertsubmission1.databinding.FragmentTvBinding
import com.gracielo.expertsubmission1.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class TVFragment : Fragment() {

    private lateinit var binding: FragmentTvBinding
    private val tvViewModel: TVViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val adapters = TVAdapter()
            adapters.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.TYPE_FILM, "TV")
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)

            }
            tvViewModel.tv.observe(viewLifecycleOwner, { listTV ->
                if (listTV != null) {
                    when (listTV) {
                        is com.gracielo.core.data.Resource.Loading -> {
                            Log.d("fragment", "Loading")
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is com.gracielo.core.data.Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapters.setData(listTV.data)
                        }

                        is com.gracielo.core.data.Resource.Error -> {
                            Log.d("fragment", "Error")
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                listTV.message ?: "Oops Something Wrong..."
                        }

                    }
                }

            })
            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapters
            }

        }
    }
}