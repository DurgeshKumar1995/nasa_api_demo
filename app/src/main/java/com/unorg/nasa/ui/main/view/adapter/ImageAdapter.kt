package com.unorg.nasa.ui.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.unorg.nasa.databinding.ImageItemBinding
import com.unorg.nasa.model.Photo

class ImageAdapter(private val clicked: (Photo?) -> Unit) :
    PagingDataAdapter<Photo, ImageAdapter.PlayersViewHolder>(
        PlayersDiffCallback()
    ) {


    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {

        val data = getItem(position)

        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {

        return PlayersViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class PlayersViewHolder(
        private val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Photo?) {
            binding.model = data
            binding.let {
                it.root.setOnClickListener {
                    clicked.invoke(data)
                }
            }

        }
    }

    private class PlayersDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

}