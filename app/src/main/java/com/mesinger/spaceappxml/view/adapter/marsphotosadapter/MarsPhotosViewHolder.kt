package com.mesinger.spaceappxml.view.adapter.marsphotosadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.databinding.ItemMarsPhotosBinding
import com.mesinger.spaceappxml.service.model.marsrover.Photo

class MarsPhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(photo: Photo){
        val binding = ItemMarsPhotosBinding.bind(itemView)
        binding.titleTextView.text = photo.rover.name
        Glide.with(itemView)
            .load(photo.imgSrc)
            .into(binding.marsPhotoImageView)
    }

}
