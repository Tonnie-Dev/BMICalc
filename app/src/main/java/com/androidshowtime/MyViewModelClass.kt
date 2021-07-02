package com.androidshowtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.ceil
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

    var weight = 10.00
    var height = 10.00

    //Height Value
    /*private val _height = MutableLiveData<Double>()
    val height: LiveData<Double>
    get() = _height*/


    //weight slider max value
    private val _weightMax = MutableLiveData<Int>()
    val weightMax: LiveData<Int>
        get() = _weightMax




    //height slider max value
    private val _heightMax = MutableLiveData<Int>()
    val heightMax: LiveData<Int>
        get() = _heightMax

    //weight Unit
    private val _weightUnit = MutableLiveData<String>()
    val weightUnit: LiveData<String>
        get() = _weightUnit

    //height Unit
    private val _heightUnit = MutableLiveData<String>()
    val heightUnit: LiveData<String>
        get() = _heightUnit

    private val _zeroError = MutableLiveData<String>()
    val zeroError: LiveData<String>
    get() = _zeroError

    fun toggleWeightSwitch(checked: Boolean) {

        when (checked) {

            true -> {

                _weightMax.value = 330
                _weightUnit.value = "lb"
                weight = getWeight(weightSliderValue.value!!, true)
                resetWeightSlider()

            }
            else -> {

                _weightMax.value = 150
                _weightUnit.value = "kg"
                resetWeightSlider()
            }
        }


    }


    fun toggleHeightSwitch(checked: Boolean) {

        when (checked) {

            true -> {

                _heightMax.value = 7
                height = getHeight(heightSliderValue.value!!, inchSliderValue.value!!)
                _heightUnit.value = "ft"
                resetHeightSliders()

            }
            else -> {

                _heightMax.value = 200
                height = getHeight(heightSliderValue.value!!, 0.0F)
                _heightUnit.value = "cm"
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

        ceil(height)
        val isGreaterThanZero = (weight.toInt() >0 && ceil(height).toInt() >0)
        if (isGreaterThanZero){
            val bmi = weight / height.pow(2)
            _bmi.value = bmi

        }


        else{

            _zeroError.value = "Weight/Height should be greater than zero"
        }

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

    private fun resetHeightSliders() {


        heightSliderValue.value = 0F
        inchSliderValue.value = 0F
    }



}