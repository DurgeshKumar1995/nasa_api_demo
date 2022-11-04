package com.unorg.nasa.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.unorg.nasa.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadAdapter(view: AppCompatImageView,imageUrl:String?){
        view.load(imageUrl){
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
    }
}