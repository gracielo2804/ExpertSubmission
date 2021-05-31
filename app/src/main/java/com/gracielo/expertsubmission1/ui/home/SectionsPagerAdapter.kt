package com.gracielo.expertsubmission1.ui.home

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gracielo.expertsubmission1.ui.movie.MovieFragment
import com.gracielo.expertsubmission1.ui.tv.TVFragment


class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        Log.d("fragment", position.toString())
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = MovieFragment()
                Log.d("fragment", "Movie")
            }
            1 -> fragment = TVFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }


}


