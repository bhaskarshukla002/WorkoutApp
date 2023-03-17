package com.example.workoutapp.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW="METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW="US_UNITS_VIEW"
    }


    private var currentVisibleView: String= METRIC_UNITS_VIEW
    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        makeVisibleMetricUnitView()



        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }


        binding?.rgUnits?.setOnCheckedChangeListener{_,checkedID:Int->
            if(checkedID== R.id.rbMetricUnits){
                makeVisibleMetricUnitView()
            }else{
                makeVisibleMetricUsUnitView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }

    }


    private fun displayBMIResult(bmi:Float){
        val bmiLabel:String
        val bmiDescription:String

        if(bmi.compareTo(15f)<=0){
            bmiLabel="very severely underweight"
            bmiDescription="Oops! You really need to take better care of yourself! Eat More"
        }else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmiLabel="severely underweight"
            bmiDescription="Oops! You really need to take better care of yourself! Eat More"
        }else if(bmi.compareTo(16f)>0 && bmi.compareTo(17.5f)<=0){
            bmiLabel="underweight"
            bmiDescription="Eat More"
        }else {
            bmiLabel="good"
        bmiDescription="Eat Healthy"
        }
        val bmiValue=BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llDiplayBMIResult?.visibility= View.VISIBLE
        binding?.tvBMIValue?.text=bmiValue
        binding?.tvBMIType?.text=bmiLabel
        binding?.tvBMIDescription?.text=bmiDescription
    }

    private fun makeVisibleMetricUnitView(){
        currentVisibleView= METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility=View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.GONE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDiplayBMIResult?.visibility=View.INVISIBLE

    }
    private fun makeVisibleMetricUsUnitView(){
        currentVisibleView= US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility=View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility=View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility=View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility=View.VISIBLE

        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()
        binding?.etUsMetricUnitWeight?.text!!.clear()

        binding?.llDiplayBMIResult?.visibility=View.INVISIBLE

    }


    private fun validateMetricUnits():Boolean{
        var isValid=true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false}
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView== METRIC_UNITS_VIEW){
            if(validateMetricUnits()){
                val heightValue:Float=binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue:Float=binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi= weightValue/(heightValue*heightValue)
                displayBMIResult(bmi)
            }
            else {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }else{
            if(currentVisibleView== US_UNITS_VIEW) {
                if (validateUsUnits()) {
                    val usUnitHeightValueFeet = binding?.etUsMetricUnitHeightFeet?.text.toString()
                    val usUnitHeightValueInch = binding?.etUsMetricUnitHeightInch?.text.toString()
                    val usUnitWeightValue: Float =
                        binding?.etUsMetricUnitWeight?.text.toString().toFloat()
                    val heightValue=usUnitHeightValueFeet.toFloat()+usUnitHeightValueInch.toFloat()*12
                    val bmi = 703*(usUnitWeightValue/(heightValue*heightValue))
                        displayBMIResult(bmi)
                } else {
                    Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun validateUsUnits():Boolean{
        var isValid=true

        if(binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        }else if(binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()) {
            isValid = false}
        else if(binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid
    }


}