package com.androidshowtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModelClass : ViewModel() {

    //Sliders
    val weightSliderValue = MutableLiveData(0.0F)
    val heightSliderValue = MutableLiveData(0.0F)
    val inchSliderValue = MutableLiveData(0.0F)

    //Switches
    val isPoundsChecked = MutableLiveData<Boolean>()
    val isFeetChecked = MutableLiveData<Boolean>()

    //Height
   // private val _height


    fun getHeight(): Double {

        return when (isFeetChecked.value) {

            true -> {

                (heightSliderValue.value?.times(0.3048))!! + (inchSliderValue.value?.times(0.0254))!!
            }
            else -> {

                (heightSliderValue.value?.div(100))?.toDouble()!!


            }
        }
    }


    fun getWeight(count:Float): Double {

      return when(isPoundsChecked.value){

          true -> {

              count.div(2.20462)
          }
          else -> {

              count.toDouble()
          }
      }



    }


}