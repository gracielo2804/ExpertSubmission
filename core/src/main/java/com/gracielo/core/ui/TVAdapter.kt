package com.gracielo.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gracielo.core.R
import com.gracielo.core.databinding.ItemDesignCatalogueBinding
import com.gracielo.core.domain.model.TV

class TVAdapter : RecyclerView.Adapter<TVAdapter.ViewHolder>() {

    private var listData = ArrayList<TV>()
    var onItemClick: ((TV) -> Unit)? = null

    fun setData(newListData: List<TV>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemDesignCatalogueBinding.bind(itemView)
        fun bind(data: TV) {
            with(binding) {
                txtJudulItem.text = data.name
                txtYearItem.text = data.firstAirDate
                txtDescItem.text = data.genreIds
                rbStar.rating = data.voteAverage!!.toFloat() / 2
                Glide.with(itemView.context)
                    .load("http://image.tmdb.org/t/p/w500//${data.posterPath}")
                    .error(R.drawable.default_min)
                    .placeholder(R.drawable.default_min)
                    .into(gambarFilm)
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listData[layoutPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_design_catalogue, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}