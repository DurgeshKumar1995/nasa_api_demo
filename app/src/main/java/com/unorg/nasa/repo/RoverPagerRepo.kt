package com.unorg.nasa.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.unorg.nasa.impl.PagerAppImpl
import com.unorg.nasa.model.Photo
import com.unorg.nasa.network.InterfaceGlobalAPI


class RoverPagerRepo(private val globalAPI: InterfaceGlobalAPI) {

    fun getSearchResultStream(roverType: String,solKey:Int): LiveData<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagerAppImpl(globalAPI, roverType, solKey) }
        ).liveData
    }

}