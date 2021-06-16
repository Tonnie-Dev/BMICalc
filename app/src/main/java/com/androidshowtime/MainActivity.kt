
package com.androidshowtime


import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.androidshowtime.databinding.ActivityMainBinding
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MyViewModelClass by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //link viewModels together
        binding.viewModel = viewModel

        //make binding observe LiveData
        binding.lifecycleOwner = this

        //Initialize Timber

        Timber.i("inside oncreate")
        val myNewSection = Section(0f, 0.46f, Color.RED, 40f, Style.ROUND)
        //binding.speedView.addSections(myNewSection)


        //Observe weight slider
        viewModel.weightSliderValue.observe(this) {

                count ->
            Timber.i("The Weight count is now $count")

            binding.weightTextView.text = count.toString()


        }

        //Observe Height slider
        viewModel.heightSliderValue.observe(this) {

                count ->
            Timber.i("The Heigh count is now $count")

            binding.heightTextView.text = count.toString()

        }

        //Observe Inch slider
        viewModel.inchSliderValue.observe(this) {
  
                count ->
            Timber.i("The Inch count is now $count")
            binding.inchTextView.text = count.toString()
        }

        //Observe weight toggle
        viewModel.isPoundsChecked.observe(this) {

                checked ->
            viewModel.toggleWeightSwitch(checked)


        }

        //Observe height toggle
        viewModel.isFeetChecked.observe(this) {

                checked ->
            viewModel.toggleHeightSwitch(checked)


        }
        //Observe BMI value
        viewModel.bmi.observe(this) {

                bmi ->
            binding.speedView.speedTo(bmi.toFloat(), 2500)
        }



    }



}