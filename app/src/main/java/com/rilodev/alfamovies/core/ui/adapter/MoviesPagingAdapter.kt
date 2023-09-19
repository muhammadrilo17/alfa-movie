package com.rilodev.alfamovies.core.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rilodev.alfamovies.BuildConfig
import com.rilodev.alfamovies.core.domain.model.MovieModel
import com.rilodev.alfamovies.databinding.MovieItemBinding

class MoviesPagingAdapter(private val context: Context) :
    PagingDataAdapter<MovieModel, MoviesPagingAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((MovieModel) -> Unit)? = null

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieModel: MovieModel) {

            Glide.with(context)
                .load(BuildConfig.BASE_IMAGE_URL+movieModel.posterPath)
                .into(binding.image)

            binding.movieName.text = movieModel.title
            binding.averageRating.text = (movieModel.voteAverage).toString()
            binding.totalVoter.text = (movieModel.voteCount).toString()
        }

        init {
            binding.root.setOnClickListener { _ ->
                val data = getItem(absoluteAdapterPosition)
                if(data != null) {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null) {
            holder.bind(data)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}