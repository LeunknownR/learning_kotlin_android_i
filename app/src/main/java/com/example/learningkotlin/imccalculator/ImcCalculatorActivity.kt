package com.example.learningkotlin.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.learningkotlin.R
import com.example.learningkotlin.imccalculator.domain.ImcData
import com.example.learningkotlin.imccalculator.utils.Gender
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var cvMaleGender: CardView
    private lateinit var cvFemaleGender: CardView
    private lateinit var tvHeight: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var data: ImcData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        setup()
    }
    private fun setup() {
        data = ImcData()
        initGenderCards()
        initHeightRangeSlider()
        initWeightCard()
        initAgeCard()
        initBtnCalculate()
    }
    private fun toColor(color: Int): Int {
        return ContextCompat.getColor(this, color)
    }
    //<editor-fold desc="Gender" default-state="collapsed">
    private fun initGenderCards() {
        cvMaleGender = findViewById(R.id.cvMale)
        cvFemaleGender = findViewById(R.id.cvFemale)
        cvMaleGender.setOnClickListener {
            data.gender = Gender.Male
            chooseCvGender()
        }
        cvFemaleGender.setOnClickListener {
            data.gender = Gender.Female
            chooseCvGender()
        }
    }
    private fun chooseCvGender() {
        when(data.gender) {
            Gender.Male -> chooseCvMaleGender()
            Gender.Female -> chooseCvFemaleGender()
            else -> throw Exception("Unexpected error")
        }
    }
    private fun chooseCvMaleGender() {
        cvMaleGender.setCardBackgroundColor(toColor(R.color.imc_calc_card_background_selected))
        cvFemaleGender.setCardBackgroundColor(toColor(R.color.imc_calc_card_background))
    }
    private fun chooseCvFemaleGender() {
        cvFemaleGender.setCardBackgroundColor(toColor(R.color.imc_calc_card_background_selected))
        cvMaleGender.setCardBackgroundColor(toColor(R.color.imc_calc_card_background))
    }
    //</editor-fold>
    //<editor-fold desc="Height" default-state="collapsed">
    private fun initHeightRangeSlider() {
        tvHeight = findViewById(R.id.tvHeight)
        findViewById<RangeSlider>(R.id.rsHeight).addOnChangeListener { _, value, _ ->
            data.height = value.toDouble()
            displayHeight()
        }
        displayHeight()
    }
    private fun displayHeight() {
        val newHeight: String = DecimalFormat("# cm").format(data.height)
        tvHeight.text = newHeight
    }
    //</editor-fold>
    //<editor-fold desc="Weight" default-state="collapsed">
    private fun initWeightCard() {
        tvWeight = findViewById(R.id.tvWeight)
        findViewById<FloatingActionButton>(R.id.btnWeightPlus).setOnClickListener {
            val newWeight = data.weight + 1
            if (!ImcData.isValidWeight(newWeight))
                return@setOnClickListener
            data.weight = newWeight
            displayWeight()
        }
        findViewById<FloatingActionButton>(R.id.btnWeightMinus).setOnClickListener {
            val newWeight = data.weight - 1
            if (!ImcData.isValidWeight(newWeight))
                return@setOnClickListener
            data.weight = newWeight
            displayWeight()
        }
        displayWeight()
    }
    private fun displayWeight() {
        tvWeight.text = getString(R.string.imc_calc_weight_text, data.weight.toString())
    }
    //</editor-fold>
    //<editor-fold desc="Age" default-state="collapsed">
    private fun initAgeCard() {
        tvAge = findViewById(R.id.tvAge)
        findViewById<FloatingActionButton>(R.id.btnAgePlus).setOnClickListener {
            val newAge = data.age + 1
            if (!ImcData.isValidAge(newAge))
                return@setOnClickListener
            data.age = newAge
            displayAge()
        }
        findViewById<FloatingActionButton>(R.id.btnAgeMinus).setOnClickListener {
            val newAge = data.age - 1
            if (!ImcData.isValidAge(newAge))
                return@setOnClickListener
            data.age = newAge
            displayAge()
        }
        displayAge()
    }
    private fun displayAge() {
        tvAge.text = getString(R.string.imc_calc_age_text, data.age.toString())
    }
    //</editor-fold>
    private fun displayErrorMessage() {
        Toast.makeText(this, "Existen datos inválidos o incompletos", Toast.LENGTH_SHORT).show()
    }
    private fun initBtnCalculate() {
        findViewById<Button>(R.id.btnRecalculate).setOnClickListener {
            if (!this.data.isValid()) {
                displayErrorMessage()
                return@setOnClickListener
            }
            intent = Intent(this, ImcCalculatorResultActivity::class.java)
            intent.putExtra(ImcCalculatorResultActivity.IMC_DATA_EXTRA_KEY, data)
            startActivity(intent)
        }
    }
}