package com.gracielo.favorite.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gracielo.core.ui.TVAdapter
import com.gracielo.expertsubmission1.ui.detail.DetailActivity
import com.gracielo.favorite.databinding.FragmentTvFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentTvFavoriteBinding
    private val tvFavoriteViewModel: TVFavoriteViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvFavoriteBinding.inflate(inflater, container, false)
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

            tvFavoriteViewModel.favTV.observe(viewLifecycleOwner, { listTV ->
                adapters.setData(listTV)
                binding.tvError.visibility =  if (listTV.isNotEmpty()) View.GONE else View.VISIBLE

            })
            with(binding.rvTvFav) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapters
            }

        }
    }

}