package com.unorg.nasa.ui.main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unorg.nasa.repo.APIRepository
import com.unorg.nasa.utils.Rovers
import kotlinx.coroutines.launch

class MainViewModel(private val apiRepository: APIRepository):ViewModel() {


    fun getPhotos(roverType: String,solKey:Int){
        viewModelScope.launch {
           val data=  apiRepository.getPhotos(roverType,solKey,1)
            println("Response:::::::: $data")
        }
    }

}