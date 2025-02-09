package com.example.workoutapp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.workoutapp.databinding.ActivityBmiBinding
import com.example.workoutapp.databinding.DialogCustomBackConfirmationBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }
    private var currentVisibleView : String = METRIC_UNITS_VIEW

    private var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI Calculator"
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
        binding?.btnCalculateBmi?.setOnClickListener {
            calculateUnits()
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            callback.handleOnBackPressed()
        }
        makeVisibleMetricUnits()
        binding?.rgUnits?.setOnCheckedChangeListener { group, checkedId: Int ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnits()
            } else {
                makeVisibleUsUnits()
            }
        }
    }
    private fun makeVisibleMetricUnits(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE

        binding?.tilMetricUnitWeight?.hint = "Weight (in KG)"
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.INVISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.INVISIBLE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    }
    private fun makeVisibleUsUnits(){
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE

        binding?.tilMetricUnitWeight?.hint = "Weight (in lbs)"
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()
        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    }
    private fun displayBMIResult(bmi : Float){

        val bmiLabel : String
        val bmiDesc : String

        if(bmi.compareTo(15f) <=0){
            bmiLabel = "Very severely underweight"
            bmiDesc = "You need to take care of yourself! Eat more"
        }
        else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <=0){
            bmiLabel = "Underweight"
            bmiDesc = "You need to take care of yourself! Eat mode"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <=0){
            bmiLabel = "Normal"
            bmiDesc = "Congratulations! You are in good shape"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <=0){
            bmiLabel = "Overweight"
            bmiDesc = "You need to take care of yourself! Workout"
        }
        else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <=0){
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDesc = "You need to take care of yourself! Workout"
        }
        else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <=0){
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDesc = "You need to take care of yourself! Workout"
        }
        else{
            bmiLabel = "Obese Class ||| (Very severely obese)"
            bmiDesc = "You need to take care of yourself! Workout"
        }

        val bmiVal = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiVal
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDesc
    }
    private fun validateMetricUnit() : Boolean {
        var isValid = true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnit()) {
                val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat()
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue/100 * heightValue/100)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@BMIActivity,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }else{
            if(validateUsUnit()){
                val heightValueFeet : String = binding?.etUsMetricUnitHeightFeet?.text.toString()
                val heightValueInch : String = binding?.etUsMetricUnitHeightInch?.text.toString()
                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val heightValue = heightValueInch.toFloat() + heightValueFeet.toFloat() * 12

                val bmi = 703 * (weightValue/(heightValue*heightValue))
                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateUsUnit() : Boolean {
        var isValid = true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
}