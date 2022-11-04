package com.unorg.nasa.repo

import com.unorg.nasa.model.RoverResponseModel
import com.unorg.nasa.network.Resource


interface APIRepository  {

    suspend fun getPhotos(roverType: String,solKey:Int,pageId:Int): Resource<RoverResponseModel>


}