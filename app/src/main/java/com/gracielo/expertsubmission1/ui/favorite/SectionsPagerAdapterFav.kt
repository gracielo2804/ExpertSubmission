package com.gracielo.jetpacksubmission3v2.Favourite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gracielo.expertsubmission1.ui.favorite.movie.MovieFavoriteFragment
import com.gracielo.expertsubmission1.ui.favorite.tv.TVFavoriteFragment


class SectionsPagerAdapterFav(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {

        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFavoriteFragment()
            1 -> fragment = TVFavoriteFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }


}


