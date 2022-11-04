package com.unorg.nasa.ui.welcome.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unorg.nasa.utils.Rovers

class WelcomeViewModel:ViewModel() {

    private val _roversTypeText = MutableLiveData<String>()
    val roversTypeText : LiveData<String> = _roversTypeText

    fun buttonClick(roversType:Int){
        val roverTypeString = when(roversType){
            1 -> Rovers.Spirit
            2 -> Rovers.Opportunity
            else -> Rovers.Curiosity
        }
        _roversTypeText.postValue(roverTypeString)

    }

}