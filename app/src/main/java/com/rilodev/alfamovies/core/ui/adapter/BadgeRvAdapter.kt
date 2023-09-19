package com.rilodev.alfamovies.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rilodev.alfamovies.core.domain.model.GenreModel
import com.rilodev.alfamovies.databinding.BadgeItemBinding

class BadgeRvAdapter(private val itemList: List<GenreModel>) :
    RecyclerView.Adapter<BadgeRvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: BadgeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genreModel: GenreModel) {
            binding.labelName.text = genreModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = BadgeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
