package com.example.chemistrybook

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.chemistrybook.databinding.ItemPageBinding

class BookPageAdapter(
    private val context: Context,
    private val totalPages: Int
) : RecyclerView.Adapter<BookPageAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val binding = ItemPageBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val pageNumber = position + 1
        val imageName = "page-${String.format("%03d", pageNumber)}.webp"
        val imagePath = "file:///android_asset/images/$imageName"

        Glide.with(context)
            .load(imagePath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.holo_red_light)
            .into(holder.binding.pageImage)

        holder.binding.pageNumberText.text = context.getString(R.string.page_number, pageNumber, totalPages)
    }

    override fun getItemCount(): Int = totalPages

    class PageViewHolder(val binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root)
}
