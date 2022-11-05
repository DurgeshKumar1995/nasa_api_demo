package com.unorg.nasa.ui.main.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.unorg.nasa.model.Photo
import com.unorg.nasa.repo.APIRepository
import com.unorg.nasa.repo.RoverPagerRepo
import kotlinx.coroutines.launch

class MainViewModel(private val apiRepository: APIRepository,private val pagerRepo: RoverPagerRepo):ViewModel() {


    fun getPhotos(roverType: String,solKey:Int){
        viewModelScope.launch {
           val data=  apiRepository.getPhotos(roverType,solKey,1)
            println("Response:::::::: $data")
        }
    }




    fun getData(roverType: String,solKey:Int):LiveData<PagingData<Photo>>{
        return pagerRepo.getSearchResultStream(roverType, solKey).cachedIn(viewModelScope)
    }

}