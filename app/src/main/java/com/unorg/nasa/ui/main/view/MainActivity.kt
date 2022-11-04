package com.unorg.nasa.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.unorg.nasa.R
import com.unorg.nasa.databinding.ActivityMainBinding
import com.unorg.nasa.ui.main.view_model.MainViewModel
import com.unorg.nasa.utils.Constants
import com.unorg.nasa.utils.NetworkUtil
import com.unorg.nasa.utils.Rovers
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        val roverType = if(intent.hasExtra(Constants.ROVER_TYPE)){
            try {
                intent.getStringExtra(Constants.ROVER_TYPE)?:Rovers.Curiosity
            }catch (e:Exception){
                Rovers.Curiosity
            }
        }else{
            Rovers.Curiosity
        }
        val sol = if(intent.hasExtra(Constants.ROVER__SOL)){
            try {
                intent.getIntExtra(Constants.ROVER__SOL,100)
            }catch (e:Exception){
                100
            }
        }else{
            100
        }

        if (NetworkUtil.isOnline(this)){
            viewModel.getPhotos()
        }else{
            Toast.makeText(baseContext, getString(R.string.connect_to_internet), Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}