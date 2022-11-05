package com.unorg.nasa.ui.details.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.unorg.nasa.R
import com.unorg.nasa.databinding.ActivityImageDetailsBinding
import com.unorg.nasa.model.Photo
import com.unorg.nasa.utils.Constants

class ImageDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivityImageDetailsBinding.inflate(layoutInflater).also { binding = it }.root
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            if (intent.hasExtra(Constants.IMADE_DETAIL_KEY)) {
                val imageData: Photo? = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra(Constants.IMADE_DETAIL_KEY, Photo::class.java)
                } else {
                    intent.getParcelableExtra(Constants.IMADE_DETAIL_KEY)
                }
                if (imageData == null) {
                    Toast.makeText(baseContext, getString(R.string.no_data), Toast.LENGTH_SHORT)
                        .show()
                    finish()
                    return
                }
                binding.imageModel = imageData

            } else {
                Toast.makeText(baseContext, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
                finish()
            }
        }catch (e:Exception){
            Toast.makeText(baseContext, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}