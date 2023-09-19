package com.rilodev.alfamovies.pages.info_movie.general

import android.app.ActionBar.LayoutParams
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.domain.model.GenreModel
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.helpers.Constants.EXTRA_DATA
import com.rilodev.alfamovies.core.ui.adapter.BadgeRvAdapter
import com.rilodev.alfamovies.core.ui.base.BaseFragment
import com.rilodev.alfamovies.databinding.FragmentGeneralBinding
import java.lang.StringBuilder

class GeneralFragment : BaseFragment<FragmentGeneralBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGeneralBinding
        get() = FragmentGeneralBinding::inflate

    override fun setup() {
        extraDataBundle()
    }

    private fun extraDataBundle() {
        val bundle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(EXTRA_DATA, MovieModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(EXTRA_DATA)
        }

        if (bundle != null) {
            initData(bundle)
        }
    }

    private fun initData(bundle: MovieModel) {
        with(binding) {
            overview.text = bundle.overview
            title.text = bundle.title
            voteCount.text = StringBuilder("(${bundle.voteCount})")
            averageRating.text = bundle.voteAverage.toString()
            releaseDate.text = bundle.releaseDate

            initGenre(bundle.genreIds)

            if(bundle.adult) {
                ivAdult.setImageResource(R.drawable.ic_adult)
                textAdult.text = resources.getString(R.string.adult)
            } else {
                ivAdult.setImageResource(R.drawable.movie_ic)
                textAdult.text = resources.getString(R.string.all_age)
            }
        }
    }

    private fun initGenre(data: List<GenreModel>) {
        val adapter = BadgeRvAdapter(data)
        binding.rvGenre.adapter = adapter

        val layoutManager = GridLayoutManager(context, 2)
        binding.rvGenre.layoutManager = layoutManager
    }
}