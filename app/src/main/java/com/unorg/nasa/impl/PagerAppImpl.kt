package com.unorg.nasa.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.unorg.nasa.model.Photo
import com.unorg.nasa.network.InterfaceGlobalAPI

class PagerAppImpl(private val globalAPI: InterfaceGlobalAPI,private val roverType: String,private val solKey:Int) : PagingSource<Int, Photo>(){
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 1
        return try {
            val response = globalAPI.getPhotos(roverType, solKey,position)
            val items = response.photos
            val nextKey = if (items.isNullOrEmpty() || items.size<25) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = items?: emptyList(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


}