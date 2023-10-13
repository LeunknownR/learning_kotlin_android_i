package com.example.learningkotlin.imccalculator.utils

import android.content.Context
import com.example.learningkotlin.R

class ImcCalculatorHelpers {
    companion object {
        fun getGenderText(context: Context, gender: Gender?): String {
            return when (gender) {
                Gender.Male -> context.getString(R.string.imc_calc_male)
                Gender.Female -> context.getString(R.string.imc_calc_female)
                else -> "Helicóptero"
            }
        }
    }
}