package com.androidshowtime


import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.androidshowtime.databinding.ActivityMainBinding
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var weightUnit = ""
    private var heightUnit = ""

    val viewModel: MyViewModelClass by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize Timber
        Timber.plant(Timber.DebugTree())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //link viewModels together
        binding.viewModel = viewModel

        //make binding observe LiveData
        binding.lifecycleOwner = this



        binding.speedView.clearSections()
        binding.speedView.addSections(
            Section(0f, .368f, Color.LTGRAY, binding.speedView.speedometerWidth, Style.BUTT),
            Section(.370f, .500f, Color.GREEN, binding.speedView.speedometerWidth, Style.BUTT),
            Section(.502f, .600f, Color.YELLOW, binding.speedView.speedometerWidth, Style.BUTT),
            Section(.602f, 1f, Color.RED, binding.speedView.speedometerWidth, Style.BUTT)
        )
        //Observe weight toggle
        viewModel.isPoundsChecked.observe(this) {

                isChecked ->
            viewModel.toggleWeightSwitch(isChecked)

        }

        //Observe height toggle
        viewModel.isFeetChecked.observe(this) {

                checked ->
            viewModel.toggleHeightSwitch(checked)


        }
        //observe weight Unit
        viewModel.weightUnit.observe(this){

             unit ->

            weightUnit = unit
        }

        //observe height Unit
        viewModel.heightUnit.observe(this  ){

            unit ->

            heightUnit = unit
        }

        //Observe weight slider
        viewModel.weightSliderValue.observe(this) {

                count ->

            val intCount = count.toInt()
            binding.weightTextView.text =
                getString(R.string.weight_label, intCount, weightUnit)


        }

        //Observe Height slider
        viewModel.heightSliderValue.observe(this) {

                count ->

            val intCount = count.toInt()
            binding.heightTextView.text = getString(R.string.height_label,intCount, heightUnit)


        }

        //Observe Inch slider
        viewModel.inchSliderValue.observe(this) {

                count ->
            val inchCount = count.toInt()
            binding.inchTextView.text = getString(R.string.inch_label, inchCount )
        }
        //Observe BMI value
        viewModel.bmi.observe(this) {

                bmi ->
            binding.speedView.speedTo(bmi.toFloat(), 2500)
            val range = viewModel.getBMIRange(
                bmi,
                getString(R.string.underweight_text),
                getString(R.string.normal_bmi_text),
                getString(R.string.overweight_bmi_text),
                getString(R.string.obese_bmi_text)
            )
            showSnackbar(range)
        }

        //Oberve for Zero Error

        viewModel.zeroError.observe(this){

                error -> showSnackbar(error)

        }
    }


    private fun showSnackbar(message: String) {

        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}