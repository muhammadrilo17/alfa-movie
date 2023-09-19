package com.rilodev.alfamovies.core.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rilodev.alfamovies.BuildConfig
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.domain.model.ProductionCompanyModel
import com.rilodev.alfamovies.databinding.ProductionCompaniesItemBinding

class CompaniesRvAdapter(private val context: Context) :
    RecyclerView.Adapter<CompaniesRvAdapter.ViewHolder>() {
    private var itemList: List<ProductionCompanyModel> = emptyList()

    inner class ViewHolder(private val binding: ProductionCompaniesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(company: ProductionCompanyModel) {
            binding.companyName.text = company.name
            Glide.with(context)
                .load(BuildConfig.BASE_IMAGE_URL+company.logoPath)
                .placeholder(R.drawable.ic_broken)
                .into(binding.companyIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ProductionCompaniesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    fun setData(itemList: List<ProductionCompanyModel>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}
