package com.gracielo.expertsubmission1.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV
import com.gracielo.expertsubmission1.R
import com.gracielo.expertsubmission1.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    lateinit var data: Any

    var type: String = ""

    companion object {
        const val TYPE_FILM = "type"
        const val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type = intent.getStringExtra(TYPE_FILM)!!
        if (type == "Movie"){
            data = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie
            supportActionBar?.title="Detail Movie"
        }
        else{
            data = intent.getParcelableExtra<TV>(EXTRA_DATA) as TV
            supportActionBar?.title="Detail TV Shows"
        }
        showDetail(type)

    }

    fun showDetail(type: String) {

        if (type == "Movie") {
            val dataMovie: Movie = data as Movie
            dataMovie.let {
                binding.txtJudulDet.text = it.title
                binding.txtDescDet.text = it.overview
                binding.txtTahunDet.text = it.releaseDate
                Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500//${it.posterPath}")
                    .error(R.drawable.default_min)
                    .placeholder(R.drawable.default_min)
                    .into(binding.gambarFilmDet)
                var statusFav = it.isFavorite
                setFavoriteState(statusFav)
                binding.fabFavorite.setOnClickListener {
                    statusFav = !statusFav
                    detailViewModel.setFavoriteMovie(dataMovie, statusFav)
                    setFavoriteState(statusFav)
                    if (!statusFav) {
                        showSnackBar("${dataMovie.title} Removed from favorite")
                    } else {
                        showSnackBar("${dataMovie.title} Added to favorite")
                    }
                }
            }
        } else {
            val dataTV: TV = data as TV
            dataTV.let {
                binding.txtJudulDet.text = it.name
                binding.txtDescDet.text = it.overview
                binding.txtTahunDet.text = it.firstAirDate
                Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500//${it.posterPath}")
                    .error(R.drawable.default_min)
                    .placeholder(R.drawable.default_min)
                    .into(binding.gambarFilmDet)

                var statusFav = it.isFavorite
                setFavoriteState(statusFav)
                binding.fabFavorite.setOnClickListener {
                    statusFav = !statusFav
                    detailViewModel.setFavoriteTV(dataTV, statusFav)
                    setFavoriteState(statusFav)
                    if (!statusFav) {
                        showSnackBar("${dataTV.name} Removed from favorite")
                    } else {
                        showSnackBar("${dataTV.name} Added to favorite")
                    }
                }
            }
        }
    }

    private fun setFavoriteState(status: Boolean) {
        if (status) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_true
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_false
                )
            )
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

//    private fun setFavorite(movie: MovieEntity?, tvShow: TVEntity?) {
//        if (movie != null) {
//            if (movie.isFavorite) {
//                showSnackBar("${movie.judul} Removed from favorite")
//            } else {
//                showSnackBar("${movie.judul} Added to favorite")
//            }
//            viewModel.setFavorite()
//        } else {
//            if (tvShow != null) {
//                if (tvShow.isFavorite) {
//                    showSnackBar("${tvShow.judul} Removed from favorite")
//                } else {
//                    showSnackBar("${tvShow.judul} Added to favorite")
//                }
//                viewModel.setFavorite()
//            }
//        }
//    }
}