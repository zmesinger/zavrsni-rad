package com.mesinger.spaceappxml.view.adapter.libraryadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.databinding.ItemLibraryImageBinding
import com.mesinger.spaceappxml.service.model.nasalibrary.Item

class LibraryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Item){
        val binding = ItemLibraryImageBinding.bind(itemView)
        binding.titleTextView.text = item.data[0].title
        Glide.with(itemView)
            .load(item.links[0].href)
            .into(binding.libraryImageView)

    }

}