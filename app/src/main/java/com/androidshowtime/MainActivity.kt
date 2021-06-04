package com.androidshowtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.androidshowtime.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
 
 private lateinit var binding: ActivityMainBinding

 val viewModel:MyViewModelClass by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //link viewModels together
        binding.viewModel = viewModel

        //make binding observe LiveData
        binding.lifecycleOwner = this

        //Initialize Timber


        Timber.i("inside oncreate")
        //observer weight slider

        viewModel.weightSliderValue.observe(this){

            count -> Timber.i("The Weight count is now $count")

            binding.weightTextView.text = count.toString()




        }
        viewModel.heightSliderValue.observe(this){

                count -> Timber.i("The Heigh count is now $count")

            binding.heightTextView.text = count.toString()

        }

        viewModel.inchSliderValue.observe(this){

                count -> Timber.i("The Inch count is now $count")
            binding.inchTextView.text = count.toString()
        }


        viewModel.isPoundsChecked.observe(this){

            checked ->
            viewModel.toggleWeightSwitch(checked)


        }

        viewModel.isFeetChecked.observe(this){

            checked ->
viewModel.toggleHeightSwitch(checked)


        }

        viewModel.bmi.observe(this){

            bmi ->  binding.speedView.speedTo(bmi.toFloat(), 2000)
        }


    }
}