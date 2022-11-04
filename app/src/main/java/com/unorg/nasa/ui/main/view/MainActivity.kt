package com.unorg.nasa.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.unorg.nasa.R
import com.unorg.nasa.databinding.ActivityMainBinding
import com.unorg.nasa.model.Photo
import com.unorg.nasa.ui.main.view.adapter.ImageAdapter
import com.unorg.nasa.ui.main.view.adapter.LoadingStateAdapter
import com.unorg.nasa.ui.main.view_model.MainViewModel
import com.unorg.nasa.utils.Constants
import com.unorg.nasa.utils.NetworkUtil
import com.unorg.nasa.utils.Rovers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding
    private val imageAdapter by lazy { ImageAdapter { actionOnItemClick(it) } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)



        val roverType = if (intent.hasExtra(Constants.ROVER_TYPE)) {
            try {
                intent.getStringExtra(Constants.ROVER_TYPE) ?: Rovers.Curiosity
            } catch (e: Exception) {
                Rovers.Curiosity
            }
        } else {
            Rovers.Curiosity
        }
        val sol = if (intent.hasExtra(Constants.ROVER__SOL)) {
            try {
                intent.getIntExtra(Constants.ROVER__SOL, 100)
            } catch (e: Exception) {
                100
            }
        } else {
            100
        }




        binding.movieRecycler.adapter = imageAdapter.withLoadStateHeaderAndFooter(
            footer = LoadingStateAdapter { retry() },
            header = LoadingStateAdapter { retry() }
        )
        if (NetworkUtil.isOnline(this)) {
            //viewModel.getPhotos(roverType, sol)
            setupRequests(roverType, sol)
        } else {
            Toast.makeText(baseContext, getString(R.string.connect_to_internet), Toast.LENGTH_SHORT)
                .show()
            finish()
        }

        imageAdapter.addLoadStateListener { loadState ->

            if (loadState.mediator?.refresh is LoadState.Loading) {

                if (imageAdapter.snapshot().isEmpty()) {
                    binding.progressBar.isVisible = true
                }
                binding.btnRetry.isVisible = false

            } else {
                binding.progressBar.isVisible = false

                val error = when {
                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (imageAdapter.snapshot().isEmpty()) {
                        binding.btnRetry.isVisible = true
                    }

                }

            }
        }

    }

    private fun actionOnItemClick(photo: Photo?) {
        Toast.makeText(baseContext, "Hello", Toast.LENGTH_SHORT).show()
    }

    private fun retry() {
        imageAdapter.retry()
    }

    fun setupRequests(roverType: String,solKey:Int) {
        viewModel.getData(roverType, solKey).observe(this){
            it?.let {
                imageAdapter.submitData(lifecycle, it)
            }
        }
    }


}