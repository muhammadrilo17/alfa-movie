package com.rilodev.alfamovies.pages.info_movie.production

import android.app.ActionBar
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.data.Resource
import com.rilodev.alfamovies.core.domain.model.MovieInfoModel
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.core.helpers.Constants
import com.rilodev.alfamovies.core.helpers.Utils
import com.rilodev.alfamovies.core.ui.ViewModelFactory
import com.rilodev.alfamovies.core.ui.adapter.CompaniesRvAdapter
import com.rilodev.alfamovies.core.ui.base.BaseFragment
import com.rilodev.alfamovies.databinding.FragmentProductionBinding

class ProductionFragment : BaseFragment<FragmentProductionBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductionBinding
        get() = FragmentProductionBinding::inflate

    private lateinit var viewModel: ProductionViewModel
    private lateinit var adapterCompanies: CompaniesRvAdapter

    override fun setup() {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[ProductionViewModel::class.java]

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
        adapterCompanies = CompaniesRvAdapter(requireContext())

        binding.rvCompanies.layoutManager = layoutManager
        binding.rvCompanies.setHasFixedSize(true)
        binding.rvCompanies.adapter = adapterCompanies
    }

    private fun initView(data: MovieInfoModel) {
        binding.webBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.homepage))

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        binding.runningTime.text = Utils.formatTimeDuration(data.runtime)
        binding.numberOfBudget.text = StringBuilder("$${Utils.formatNumberWithSeparator(data.budget)}")
        binding.numberOfRevenue.text = StringBuilder("$${Utils.formatNumberWithSeparator((data.revenue).toLong())}")
        binding.tagLine.text = data.tagline
    }

    private fun loadData(bundle: MovieModel) {
        showLoading()
        viewModel.getInfoMovie(bundle.id).observe(viewLifecycleOwner) { resource ->
            when(resource) {
                is Resource.Success -> {
                    hideLoading()
                    val data = resource.data
                    if(data != null) {
                        initView(data)
                        adapterCompanies.setData(data.productionCompanies)
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
        binding.flLoading.visibility = View.VISIBLE
        binding.includeLayout.progressBar.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.text = getString(R.string.tv_loading)

        binding.llContent.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.flLoading.visibility = View.GONE
        binding.includeLayout.progressBar.visibility = View.GONE
        binding.includeLayout.tvLoading.visibility = View.GONE
        binding.includeLayout.tvLoading.text = getString(R.string.tv_loading)

        binding.llContent.visibility = View.VISIBLE
    }

    private fun hideLoadingWithError(message: String) {
        binding.flLoading.visibility = View.VISIBLE
        binding.includeLayout.progressBar.visibility = View.GONE
        binding.includeLayout.tvLoading.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.text = message

        binding.llContent.visibility = View.GONE
    }
}