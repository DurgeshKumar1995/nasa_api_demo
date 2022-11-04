package com.unorg.nasa.ui.welcome.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unorg.nasa.databinding.ActivityWelcomeBinding
import com.unorg.nasa.ui.main.view.MainActivity
import com.unorg.nasa.ui.welcome.viewModel.WelcomeViewModel
import com.unorg.nasa.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<WelcomeViewModel>()
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityWelcomeBinding.inflate(layoutInflater).also { binding = it }.root)
        binding.viewModel = viewModel

        viewModel.roversTypeText.observe(this) {
            val intent =
                Intent(this@WelcomeActivity.applicationContext, MainActivity::class.java)
            val sol = try {
                val solTemp = binding.edtTex.text.toString()
                if (solTemp.isEmpty()) {
                    100
                } else {
                   when( solTemp.toInt()){
                       10,100,1000 -> solTemp.toInt()
                       else -> 100
                   }
                }
            } catch (e: Exception) {
                100
            }
            intent.putExtra(Constants.ROVER_TYPE,it)
            intent.putExtra(Constants.ROVER__SOL,sol)
            startActivity(intent)
        }

    }
}