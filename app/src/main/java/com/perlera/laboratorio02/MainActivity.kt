package com.perlera.laboratorio02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var weigthEditText: EditText
    private lateinit var heigthEditText: EditText
    private lateinit var actionCalculate: Button
    private lateinit var bmiTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var infoTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()
        setListener()
    }

    private fun bind(){
        weigthEditText = findViewById(R.id.weight_edit_text)
        heigthEditText = findViewById(R.id.height_edit_text)
        actionCalculate = findViewById(R.id.action_calculate)
        bmiTextView = findViewById(R.id.bmi_text_view)
        resultTextView = findViewById(R.id.result_text_view)
        infoTextView = findViewById(R.id.info_text_view)
    }

    private fun setListener() {
        actionCalculate.setOnClickListener {
            val weigth = weigthEditText.text.toString()
            val height = heigthEditText.text.toString()

            if (!validateInput(weigth, height)){
                clearTextView()
                return@setOnClickListener
            }

            val bmi = calculateBmi(weigth.toFloat(), height.toFloat())

            //val bmiTwoDigits = String.format("%.2f", bmi).toFloat()

            clearFocus()
            displayResult(bmi)
        }
    }

    private fun validateInput(weigth: String?, height: String?): Boolean {
        when{
            weigth.isNullOrEmpty() -> {
                Toast.makeText(this, "Weigth is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Weigth is empty", Toast.LENGTH_SHORT).show()
                return false
            }
        }

        return true
    }

    private fun calculateBmi(weigth: Float, height: Float): Float{
        return weigth / ( (height/100) * (height/100) )
    }

    private fun displayResult(bmi: Float){
        bmiTextView.text = bmi.toString()
        infoTextView.text = "(Normal range is 18.00 - 24.9)"

        var informationResult = ""
        var color = 0

        when {
            bmi < 18.5 -> {
                informationResult = getString(R.string.under_weigth_text)
                color = R.color.under_weight
            }
            bmi in 18.5..24.99 -> {
                informationResult = "NormalWeigth"
                color = R.color.normal_weight
            }
            bmi in 25.8..29.99 -> {
                informationResult = "Overweigth"
                color = R.color.over_weight
            }
            bmi > 30.00 -> {
                informationResult = "Obese"
                color = R.color.obese
            }
        }

        resultTextView.text = informationResult
        resultTextView.setTextColor(ContextCompat.getColor(this, color))
    }

    private fun clearFocus() {
        weigthEditText.clearFocus()
        heigthEditText.clearFocus()
    }

    private fun clearTextView() {
        bmiTextView.text=""
        resultTextView.text = ""
        infoTextView.text = ""
    }
}