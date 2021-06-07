package com.androidshowtime

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.slider.Slider

@InverseBindingAdapter(attribute = "android:value")
fun getSliderValue(slider: Slider) = slider.value

@BindingAdapter("android:valueAttrChanged")
fun setSliderListeners(slider: Slider, attrChange: InverseBindingListener) {
    slider.addOnChangeListener { _, _, _ ->
        attrChange.onChange()
    }
}

@BindingAdapter("sliderVisibility")
fun Slider.toggleSliderVisibility(hideSlider: Boolean) {

    visibility = if (!hideSlider) {

        View.GONE
    } else {
        View.VISIBLE

    }


}

@BindingAdapter("textViewVisibility")

fun TextView.toggleTextViewVisibility(hideTextView: Boolean) {

    visibility = if (!hideTextView) {
        View.GONE
    } else {
        View.VISIBLE
    }

}

@BindingAdapter("iconTextController")
fun TextView.setIcon(checked: Boolean){

    text = when(checked){

        true -> {

            context.getString(R.string.lbs_abbrev)
        }

        false -> {

            context.getString(R.string.kilos_abbrev)
        }
    }
}