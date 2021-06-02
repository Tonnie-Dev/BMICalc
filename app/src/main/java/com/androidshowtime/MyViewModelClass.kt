package com.androidshowtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModelClass : ViewModel() {

    val weightSliderValue = MutableLiveData<Float>()
    val heightSliderValue = MutableLiveData<Float>()
    val inchSliderValue = MutableLiveData<Float>()

 val isWeightChecked = MutableLiveData<Boolean>()
    /*val isWeightChecked: LiveData<Boolean>
        get() = _isWeightChecked*/

}