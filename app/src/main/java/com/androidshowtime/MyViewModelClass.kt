package com.androidshowtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModelClass : ViewModel() {

    //Sliders
    val weightSliderValue = MutableLiveData<Float>()
    val heightSliderValue = MutableLiveData<Float>()
    val inchSliderValue = MutableLiveData<Float>()

    //Switches
    val isPoundsChecked = MutableLiveData<Boolean>()
    val isFeetChecked = MutableLiveData<Boolean>()


fun getHeight(): Double {


    return 0.0
}


    fun getWeight(): Double {


        return 0.0
    }
}