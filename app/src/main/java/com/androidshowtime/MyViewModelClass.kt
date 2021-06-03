package com.androidshowtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MyViewModelClass : ViewModel() {

    //Sliders
    val weightSliderValue = MutableLiveData(0.0F)
    val heightSliderValue = MutableLiveData(0.0F)
    val inchSliderValue = MutableLiveData(0.0F)

    //Switches
    val isPoundsChecked = MutableLiveData<Boolean>()
    val isFeetChecked = MutableLiveData<Boolean>()

    //BMI
    private val _bmi = MutableLiveData<Double>()
    val bmi: LiveData<Double>
        get() = _bmi


    //Weight Value
    private val _weight = MutableLiveData<Double>()
    val weight: LiveData<Double>
    get() = _weight

    //Height Value
    private val _height = MutableLiveData<Double>()
    val height: LiveData<Double>
    get() = _height


    //weight slider max value
    private val _weightMax = MutableLiveData<Int>()
    val weightMax: LiveData<Int>
    get() = _weightMax

    fun getWeight()  {

         when (isPoundsChecked.value) {

            true -> {

                _weightMax.value = 330

          _weight.value =      weightSliderValue.value?.div(2.20462)!!
            }
            else -> {
                _weightMax.value = 150
                _weight.value =     weightSliderValue.value?.toDouble()!!
            }
        }


    }


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




    fun computeBMI() {

        val h = _height.value
        val w = _weight.value

        val bmi = h?.pow(2)?.let { w?.div(it) }

        _bmi.value = bmi!!
    }


    fun getBMIRange(
        bmi: Double, underWeight: String, normal: String, overWeight: String,
        obese: String): String {


        return when (bmi) {
            in 0.0..18.4 -> underWeight
            in 18.5..25.0 -> normal
            in 25.1..30.0 -> overWeight
            else -> obese
        }
    }


}