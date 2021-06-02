package com.androidshowtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModelClass : ViewModel() {

    //Sliders
    val weightSliderValue = MutableLiveData<Float>()
    val heightSliderValue = MutableLiveData<Float>()
    val inchSliderValue = MutableLiveData<Float>()

    //switches
    val isPoundsChecked = MutableLiveData<Boolean>()
    val isFeetChecked = MutableLiveData<Boolean>()



}