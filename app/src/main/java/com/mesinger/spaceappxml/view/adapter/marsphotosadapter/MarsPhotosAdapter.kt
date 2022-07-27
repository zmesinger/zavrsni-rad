package com.mesinger.spaceappxml.view.adapter.marsphotosadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.service.model.marsrover.Photo

class MarsPhotosAdapter: RecyclerView.Adapter<MarsPhotosViewHolder>() {

    private val photos = mutableListOf<Photo>()

    fun setPhotos(photos: List<Photo>){
        this.photos.clear()
        this.photos.addAll(photos)
        this.notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mars_photos, parent, false)

        return MarsPhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int {
        return photos.count()
    }
}