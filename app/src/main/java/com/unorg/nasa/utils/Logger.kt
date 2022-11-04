package com.unorg.nasa.utils

import android.util.Log
import com.unorg.nasa.BuildConfig


object Logger {

    private const val TAG = "Logger"

    fun Debug(msg:String,tag:String ?= TAG){
        if (BuildConfig.DEBUG){
            Log.d(tag,msg)
        }

    }
    fun Debug(msg:Exception?,tag:String= TAG){
        if (BuildConfig.DEBUG){
            Log.d(tag,msg?.localizedMessage!!)
        }
    }

    fun Error( msg:String,tag:String= TAG){
        if (BuildConfig.DEBUG){
            Log.e(tag,msg)
        }
    }
    fun Error( msg:Exception?,tag:String= TAG){
        if (BuildConfig.DEBUG){
            Log.e(tag,msg?.localizedMessage!!)
        }
    }

}