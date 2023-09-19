package com.rilodev.alfamovies.core.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rilodev.alfamovies.pages.info_movie.general.GeneralFragment
import com.rilodev.alfamovies.pages.info_movie.production.ProductionFragment
import com.rilodev.alfamovies.pages.info_movie.review.ReviewFragment

class InfoPagerAdapter(activity: AppCompatActivity, private val bundle: Bundle) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> GeneralFragment()
            1 -> ProductionFragment()
            else -> ReviewFragment()
        }

        fragment.arguments = bundle
        return fragment
    }
}
