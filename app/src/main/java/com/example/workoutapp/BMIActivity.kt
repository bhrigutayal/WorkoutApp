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

            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
        binding?.btnCalculateBmi?.setOnClickListener {
            if(validateMetricUnit()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat()
                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@BMIActivity,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            callback.handleOnBackPressed()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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
}