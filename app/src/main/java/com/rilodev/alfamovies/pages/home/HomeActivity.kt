package com.rilodev.alfamovies.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.helpers.Constants
import com.rilodev.alfamovies.core.helpers.Utils.errorResponse
import com.rilodev.alfamovies.core.helpers.Utils.movePage
import com.rilodev.alfamovies.core.ui.ViewModelFactory
import com.rilodev.alfamovies.core.ui.adapter.LoadingStateAdapter
import com.rilodev.alfamovies.core.ui.adapter.MoviesPagingAdapter
import com.rilodev.alfamovies.core.ui.base.BaseActivity
import com.rilodev.alfamovies.databinding.ActivityHomeBinding
import com.rilodev.alfamovies.pages.info_movie.InfoActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(applicationContext)
    }

    private lateinit var adapter: MoviesPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
    }

    override fun setup() {
        initialPagingMovies()
        initialSwipeRefreshListener()

        loadPagingMovies()
        loadMoviesData()
    }

    private fun initialPagingMovies() {
        with(binding) {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.setHasFixedSize(true)
        }
    }

    private fun loadPagingMovies() {
        adapter = MoviesPagingAdapter(this)
        adapter.onItemClick = { data ->
            val bundle = Bundle()
            bundle.putParcelable(Constants.EXTRA_DATA, data)
            movePage(InfoActivity::class.java, bundle)
        }

        adapter.addLoadStateListener {loadState ->
            when {
                loadState.prepend is LoadState.Error -> {
                    loadState.prepend as LoadState.Error
                }
                loadState.append is LoadState.Error -> {
                    loadState.append as LoadState.Error
                }
                loadState.refresh is LoadState.Error -> {
                    val result = (loadState.refresh as LoadState.Error)

                    if(adapter.itemCount <= 0) {
                        binding.includeLayout.progressBar.visibility = View.GONE

                        binding.includeLayout.tvLoading.visibility = View.VISIBLE
                        binding.includeLayout.tvLoading.text = errorResponse(result.error.message.toString())
                    } else {
                        binding.includeLayout.progressBar.visibility = View.GONE
                        binding.includeLayout.tvLoading.text = ""
                    }
                }
                else -> {}
            }
        }

        with(binding) {
            rvMovies.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
            rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((dy > 0 && binding.fabGoUp.isShown) || !recyclerView.canScrollVertically(-1)) {
                        binding.fabGoUp.hide()
                    } else if (dy < 0 && !binding.fabGoUp.isShown) {
                        binding.fabGoUp.show()
                    }
                }
            })
        }

        binding.fabGoUp.setOnClickListener {
            binding.rvMovies.smoothScrollToPosition(0)
        }
        binding.fabGoUp.hide()
    }

    private fun initialSwipeRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {

            loadMoviesData(true)
        }
    }

    private fun loadMoviesData(
        isSwipeRefresh: Boolean = false,
    ) {
        if (!isSwipeRefresh) binding.includeLayout.progressBar.visibility = View.VISIBLE
        else binding.includeLayout.progressBar.visibility = View.GONE

        binding.includeLayout.tvLoading.visibility = View.VISIBLE
        binding.includeLayout.tvLoading.text = getString(R.string.tv_loading)

//        adapter.submitData(lifecycle, PagingData.empty())
        viewModel.getMovies().observe(this) { result ->
            binding.swipeRefresh.isRefreshing = false
            binding.includeLayout.progressBar.visibility = View.INVISIBLE
            binding.includeLayout.tvLoading.text = ""

            adapter.submitData(lifecycle, result)

        }
    }
}