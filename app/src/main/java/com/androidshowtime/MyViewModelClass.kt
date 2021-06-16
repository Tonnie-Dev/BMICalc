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
    val isPoundsChecked = MutableLiveData<Boolean>(false)
    val isFeetChecked = MutableLiveData<Boolean>(false)

    //BMI
    private val _bmi = MutableLiveData<Double>()
    val bmi: LiveData<Double>
        get() = _bmi


    //Weight Value
    /*private val _weight = MutableLiveData<Double>()
    val weight: LiveData<Double>
    get() = _weight*/

    var weight = 82.0


    //Height Value
    /*private val _height = MutableLiveData<Double>()
    val height: LiveData<Double>
    get() = _height*/
    var height = 163.0

    //weight slider max value
    private val _weightMax = MutableLiveData<Int>()
    val weightMax: LiveData<Int>
        get() = _weightMax

    //height slider max value
    private val _heightMax = MutableLiveData<Int>()
    val heightMax: LiveData<Int>
        get() = _heightMax

    fun toggleWeightSwitch(checked: Boolean) {

        when (checked) {

            true -> {

                _weightMax.value = 330
                weight = getWeight(weightSliderValue.value!!, true)
                resetWeightSlider()

            }
            else -> {

                _weightMax.value = 150
                resetWeightSlider()
            }
        }


    }


    fun toggleHeightSwitch(checked: Boolean) {

        when (checked) {

            true -> {

                _heightMax.value = 7
                height = getHeight(heightSliderValue.value!!, inchSliderValue.value!!)
                resetHeightSliders()
            }
            else -> {

                _heightMax.value = 200
                height = getHeight(heightSliderValue.value!!, 0.0F)
                resetHeightSliders()

            }
        }
    }

    private fun getWeight(count: Float, isPoundsChecked: Boolean): Double {


        return when (isPoundsChecked) {

            true -> {

                count / 2.20462
            }
            else -> count.toDouble()
        }


    }

    private fun getHeight(heightCount: Float, inchCount: Float): Double {

        return when (inchCount > 0F) {

            true -> {

                (heightCount * 0.3048) + (inchCount * 0.0254)
            }
            else -> {

                (heightCount / 100).toDouble()
            }
        }


    }

    fun computeBMI() {

        weight = getWeight(weightSliderValue.value!!, isPoundsChecked.value!!)
        height = getHeight(heightSliderValue.value!!, inchSliderValue.value!!)
        val bmi = weight / height.pow(2)

        _bmi.value = bmi
    }


    fun getBMIRange(
        bmi: Double, underWeight: String, normal: String, overWeight: String,
        obese: String
    ): String {

        return when (bmi) {
            in 0.0..18.4 -> underWeight
            in 18.5..25.0 -> normal
            in 25.1..30.0 -> overWeight
            else -> obese
        }
    }


    private fun resetWeightSlider() {

        weightSliderValue.value = 0F

    }

    fun resetHeightSliders() {


        heightSliderValue.value = 0F
        inchSliderValue.value = 0F
    }
}