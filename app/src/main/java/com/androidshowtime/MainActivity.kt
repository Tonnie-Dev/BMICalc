package com.androidshowtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.androidshowtime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
 val viewModel:MyViewModelClass by activityViewModels()
 private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


    }
}