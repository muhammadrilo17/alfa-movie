package com.rilodev.alfamovies.pages.info_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.helpers.Constants.EXTRA_BUNDLE_DATA
import com.rilodev.alfamovies.core.helpers.Constants.EXTRA_DATA
import com.rilodev.alfamovies.core.ui.ViewModelFactory
import com.rilodev.alfamovies.core.ui.adapter.InfoPagerAdapter
import com.rilodev.alfamovies.core.ui.base.BaseActivity
import com.rilodev.alfamovies.databinding.ActivityInfoBinding

class InfoActivity : BaseActivity<ActivityInfoBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityInfoBinding
        get() = ActivityInfoBinding::inflate

    private lateinit var adapter: InfoPagerAdapter

    private val infoViewModel: InfoViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private var data: MovieModel? = null

    override fun setup() {
        initBundleData()

        initBackButton()
    }

    @Suppress("DEPRECATION")
    private fun initBundleData() {
        val bundle = intent.getBundleExtra(EXTRA_BUNDLE_DATA)
        if (bundle != null) {
            data = bundle.getParcelable(EXTRA_DATA)
            if (data != null) {
                initTabLayout(data!!)
                initTrailer(data!!.id)
            } else {
                Toast.makeText(this, "Data Invalid!", Toast.LENGTH_SHORT).show()
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun initTrailer(id: Int) {
        infoViewModel.getTrailerMovie(id).observe(this) { resources ->
            when (resources) {
                is Resource.Success -> {
                    val data = resources.data
                    if (data != null) {
                        lifecycle.addObserver(binding.youtubePlayer)
                        binding.youtubePlayer.addYouTubePlayerListener(object :
                            AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youTubePlayer.loadVideo(data.key, 0f)
                            }
                        })
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(this, resources.message.toString(), Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {}
            }

        }
    }

    private fun initBackButton() {
        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initTabLayout(data: MovieModel? = null) {
        val bundle = Bundle()
        bundle.putParcelable(EXTRA_DATA, data)

        adapter = InfoPagerAdapter(this, bundle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> resources.getString(R.string.general)
                1 -> resources.getString(R.string.production)
                else -> resources.getString(R.string.review)
            }
        }.attach()
    }
}