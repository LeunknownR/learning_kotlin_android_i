package com.example.learningkotlin.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.learningkotlin.R
import com.example.learningkotlin.imccalculator.domain.ImcData
import com.example.learningkotlin.imccalculator.utils.ImcCalculatorHelpers

class ImcCalculatorResultActivity : AppCompatActivity() {
    companion object {
        const val IMC_DATA_EXTRA_KEY: String = "IMC_DATA"
    }
    private lateinit var data: ImcData
    private lateinit var tvImcValue: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvImcDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator_result)
        setup()
    }
    private fun setup() {
        initBtnRecalculate()
        fillData()
        displayData()
        val imc: Double = data.calculateImc()
        displayResult(imc)
    }
    private fun fillData() {
        data = intent.getSerializableExtra(IMC_DATA_EXTRA_KEY) as ImcData
    }
    private fun displayData() {
        findViewById<TextView>(R.id.tvGender).text =  ImcCalculatorHelpers.getGenderText(this, data.gender)
        findViewById<TextView>(R.id.tvHeight).text = data.height.toString()
        findViewById<TextView>(R.id.tvWeight).text = data.weight.toString()
        findViewById<TextView>(R.id.tvAge).text = data.age.toString()
    }
    private fun displayResult(imc: Double) {
        tvImcValue = findViewById(R.id.tvImcValue)
        tvImc = findViewById(R.id.tvImc)
        tvImcDescription = findViewById(R.id.tvImcDescription)
        when (imc) {
            in 0.00..18.50 -> { // Peso bajo
                displayResultCard(imc, R.string.imc_calc_imc_low_weight, R.string.imc_calc_imc_desc_low_weight, R.color.imc_calc_dangerous_result)
            }
            in 18.51..24.99 -> { // Peso normal
                displayResultCard(imc, R.string.imc_calc_imc_normal_weight, R.string.imc_calc_imc_desc_normal_weight, R.color.imc_calc_normal_weight_result)
            }
            in 25.00..29.99 -> { // Sobrepeso
                displayResultCard(imc, R.string.imc_calc_imc_overweight_weight, R.string.imc_calc_imc_desc_overweight_weight, R.color.imc_calc_overweight_weight_result)
            }
            in 30.00..99.00 -> { // Obesidad
                displayResultCard(imc, R.string.imc_calc_imc_obesity_weight, R.string.imc_calc_imc_desc_obesity_weight, R.color.imc_calc_dangerous_result)
            }
        }
    }
    private fun displayResultCard(imc: Double, imcValueR: Int, imcDescriptionR: Int, colorR: Int) {
        tvImcValue.text = getString(imcValueR)
        tvImcValue.setTextColor(ContextCompat.getColor(this, colorR))
        tvImc.text =  imc.toString()
        tvImcDescription.text = getString(imcDescriptionR)
    }
    private fun initBtnRecalculate() {
        findViewById<Button>(R.id.btnRecalculate).setOnClickListener { onBackPressed() }
    }
}