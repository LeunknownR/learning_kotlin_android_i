package com.example.learningkotlin.imccalculator.domain

import com.example.learningkotlin.imccalculator.utils.Gender
import java.io.Serializable
import java.text.DecimalFormat

class ImcData: Serializable {
    companion object {
        private const val MIN_HEIGHT: Double = 20.0
        private const val MAX_HEIGHT: Double = 200.0
        private const val MIN_WEIGHT: Double = 3.0
        private const val MAX_WEIGHT: Double = 200.0
        private const val MIN_AGE: Int = 0
        private const val MAX_AGE: Int = 100
        fun isValidAge(age: Int): Boolean {
            return age in MIN_AGE..MAX_AGE
        }
        fun isValidWeight(weight: Double): Boolean {
            return weight in MIN_WEIGHT..MAX_WEIGHT
        }
        private fun isValidHeight(height: Double): Boolean {
            return height in MIN_HEIGHT..MAX_HEIGHT
        }
    }
    var gender: Gender?
    var height: Double
    var weight: Double
    var age: Int
    init {
        gender = null
        height = MIN_HEIGHT
        weight = MIN_WEIGHT
        age = MIN_AGE
    }
    override fun toString(): String {
        return "ImcData(Gender: ${gender?.name ?: "Helicóptero"} | Height: $height | Weight: $weight | Age: $age)"
    }
    fun isValid(): Boolean {
        return gender != null && isValidHeight(height) && isValidWeight(weight) && isValidAge(age)
    }
    fun calculateImc(): Double {
        // imc = weight(kg) / (height(m))^2
        val heightInM: Double = height / 100
        val imc: Double = weight / Math.pow(heightInM, 2.0)
        return DecimalFormat("#.##").format(imc).toDouble()
    }
}
