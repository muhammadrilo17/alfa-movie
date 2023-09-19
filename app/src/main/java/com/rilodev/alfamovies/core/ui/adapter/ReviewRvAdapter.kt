package com.rilodev.alfamovies.core.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rilodev.alfamovies.BuildConfig
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.domain.model.MovieReviewModel
import com.rilodev.alfamovies.core.helpers.Utils
import com.rilodev.alfamovies.databinding.ReviewItemBinding

class ReviewRvAdapter(private val context: Context) :
    RecyclerView.Adapter<ReviewRvAdapter.ViewHolder>() {
    private var itemList: List<MovieReviewModel> = emptyList()

    inner class ViewHolder(private val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: MovieReviewModel) {
            binding.author.text = review.author
            binding.createdTime.text = Utils.generateFriendlyDateTime(review.createdAt ?: "-")
            Glide.with(context)
                .load(BuildConfig.BASE_IMAGE_URL+review.authorDetails.avatarPath)
                .placeholder(R.drawable.ic_person)
                .into(binding.userIc)
            binding.content.text = review.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(itemList: List<MovieReviewModel>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}
