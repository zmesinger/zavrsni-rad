package com.mesinger.spaceappxml.view.adapter.libraryadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.service.model.nasalibrary.Item

class LibraryListAdapter: RecyclerView.Adapter<LibraryListViewHolder>() {
    private val items = mutableListOf<Item>()

    fun setItems(items: List<Item>){
        this.items.clear()
        this.items.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_library_image, parent, false)

        return LibraryListViewHolder(view)

    }

    override fun onBindViewHolder(holder: LibraryListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}