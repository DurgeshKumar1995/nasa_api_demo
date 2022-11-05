package com.unorg.nasa.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.unorg.nasa.R
import com.unorg.nasa.model.Photo


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadAdapter(view: AppCompatImageView,imageUrl:String?){
        view.load(imageUrl){
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
    }

    @JvmStatic
    fun shareURL(view: View,photo: Photo?){
        if (photo == null || photo.img_src.isNullOrBlank())
        {
            Toast.makeText(view.context.applicationContext, R.string.no_data, Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, photo.img_src)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        try {
            view.context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                view.context.applicationContext,
                "Send App have not been installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}