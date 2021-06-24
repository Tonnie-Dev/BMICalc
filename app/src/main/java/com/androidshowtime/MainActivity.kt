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
        //val myNewSection = Section(0f, 0.46f, Color.RED, 40f, Style.ROUND)
        //binding.speedView.addSections(myNewSection)

        binding.speedView.clearSections()
        binding.speedView.addSections(
            Section(0f, .368f, Color.LTGRAY, binding.speedView.speedometerWidth, Style.BUTT),
            Section(.370f, .500f, Color.GREEN, binding.speedView.speedometerWidth, Style.BUTT),
            Section(.502f, .600f, Color.YELLOW, binding.speedView.speedometerWidth, Style.BUTT),
            Section(.602f, 1f, Color.RED, binding.speedView.speedometerWidth, Style.BUTT)
        )
        /*binding.speedView.makeSections(4, Color.CYAN, Style.BUTT)
        // this is very simple way to add sections,
        // if your speedometer has 0-100 speed rage
        // that means every section has '20' speed-unit in space.

        // you can change colors to every:
        binding.speedView.sections[0].color = Color.RED
        binding.speedView.sections[1].color = Color.GREEN
        binding.speedView.sections[2].color = Color.YELLOW
        binding.speedView.sections[3].color = Color.RED*/


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
            val range = viewModel.getBMIRange(
                bmi,
                getString(R.string.underweight_text),
                getString(R.string.normal_bmi_text),
                getString(R.string.overweight_bmi_text),
                getString(R.string.obese_bmi_text)
            )
            showSnackbar(range)
        }

    }

    fun showSnackbar(message: String) {

        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}