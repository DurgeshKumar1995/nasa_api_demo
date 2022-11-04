package com.unorg.nasa.network

import com.unorg.nasa.model.RoverResponseModel
import com.unorg.nasa.utils.Constants
import retrofit2.http.*


interface InterfaceGlobalAPI {

    companion object{
        private const val API_KEY = "api_key"
        private const val SOL_KEY = "sol"
        private const val PAGE_KEY = "page"
        private const val ROVERS_TYPE_KEY = "roverType"
        //https://api.nasa.gov/mars-photos/api/v1/rovers/Opportunity/photos?sol=1000&&api_key=QA5nabKfBxNdbf7Ege7pArjJTpqEiCoWjQP8UtDW&page=1
    }

    @GET("{roverType}/photos")
    suspend fun getPhotos(
        @Path(ROVERS_TYPE_KEY) roverType: String,
        @Query(SOL_KEY) solKey:Int,
        @Query(PAGE_KEY) pageId:Int=1,
        @Query(API_KEY) apiKey:String=Constants.API_KEY
    ): RoverResponseModel



}