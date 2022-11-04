package com.unorg.nasa.impl

import com.unorg.nasa.model.RoverResponseModel
import com.unorg.nasa.network.BaseRepository
import com.unorg.nasa.network.InterfaceGlobalAPI
import com.unorg.nasa.network.Resource
import com.unorg.nasa.repo.APIRepository

class AppImpl(private val globalAPI: InterfaceGlobalAPI,private val baseRepository: BaseRepository) :APIRepository{


    override suspend fun getPhotos(roverType: String, solKey: Int, pageId: Int): Resource<RoverResponseModel> {
       return baseRepository.safeApiCall {
            globalAPI.getPhotos(roverType, solKey, pageId)
        }
    }
}