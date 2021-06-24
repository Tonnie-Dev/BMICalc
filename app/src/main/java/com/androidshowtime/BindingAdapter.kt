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

@BindingAdapter("iconWeightTextController")
fun TextView.setWeightIconText(checked: Boolean) {

    text = when (checked) {

        true -> {

            context.getString(R.string.pound_abbrev)
        }

        false -> {

            context.getString(R.string.kg_label)
        }
    }
}

@BindingAdapter("iconHeightTextController")
fun TextView.setHeightIconText(checked: Boolean) {

    text = when (checked) {

        true -> {

            context.getString(R.string.feet_inch_abbrev)
        }

        false -> {
            context.getString(R.string.cm_abbrev)

        }
    }
}

@BindingAdapter("showKiloDot")
fun TextView.showKiloDot(isShow: Boolean) {

    visibility = when (isShow) {

        true -> View.INVISIBLE

        false -> View.VISIBLE


    }
}

@BindingAdapter("showPoundDot")
fun TextView.showPoundDot(isShow: Boolean) {

    visibility = when (isShow) {

        true -> View.VISIBLE

false -> View.INVISIBLE}}