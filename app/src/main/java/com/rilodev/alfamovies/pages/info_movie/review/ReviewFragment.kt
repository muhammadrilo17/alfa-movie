package com.rilodev.alfamovies.pages.info_movie.review

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.helpers.Constants
import com.rilodev.alfamovies.core.ui.ViewModelFactory
import com.rilodev.alfamovies.core.ui.adapter.ReviewRvAdapter
import com.rilodev.alfamovies.core.ui.base.BaseFragment
import com.rilodev.alfamovies.databinding.FragmentReviewBinding

class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentReviewBinding
        get() = FragmentReviewBinding::inflate

    private lateinit var viewModel: ReviewViewModel
    private lateinit var reviewRvAdapter: ReviewRvAdapter

    override fun setup() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[ReviewViewModel::class.java]

        extraDataBundle()
        initCompanyList()
    }

    private fun extraDataBundle() {
        val bundle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(Constants.EXTRA_DATA, MovieModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(Constants.EXTRA_DATA)
        }

        if (bundle != null) {
            loadData(bundle)
        }
    }

    private fun initCompanyList() {
        val layoutManager = LinearLayoutManager(context)
        reviewRvAdapter = ReviewRvAdapter(requireContext())

        binding.rvReview.layoutManager = layoutManager
        binding.rvReview.setHasFixedSize(true)
        binding.rvReview.adapter = reviewRvAdapter
    }

    private fun loadData(bundle: MovieModel) {
        showLoading()
        viewModel.getReviewMovie(bundle.id).observe(viewLifecycleOwner) {resource ->
            when(resource) {
                is Resource.Success -> {
                    hideLoading()
                    val data = resource.data
                    if(data != null) {
                        reviewRvAdapter.setData(resource.data)
                    }
                }
                is Resource.Error -> {
                    hideLoadingWithError(resource.message.toString())
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    private fun showLoading() {
        binding.includeLayout.progressBar.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.text = getString(R.string.tv_loading)
        binding.flLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.includeLayout.progressBar.visibility = View.GONE
        binding.includeLayout.tvLoading.visibility = View.GONE
        binding.includeLayout.tvLoading.text = getString(R.string.tv_loading)
        binding.flLoading.visibility = View.GONE
    }

    private fun hideLoadingWithError(message: String) {
        binding.includeLayout.progressBar.visibility = View.GONE
        binding.includeLayout.tvLoading.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.text = message
        binding.flLoading.visibility = View.VISIBLE
    }

}