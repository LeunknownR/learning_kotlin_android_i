package com.example.learningkotlin.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.learningkotlin.databinding.ActivitySuperheroDetailsBinding
import com.example.learningkotlin.superheroapp.services.ApiRequestHelper
import com.example.learningkotlin.superheroapp.services.PowerStats
import com.example.learningkotlin.superheroapp.services.SuperheroDetails
import com.example.learningkotlin.superheroapp.services.SuperheroImage
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class SuperheroDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperheroDetailsBinding
    companion object {
        const val SUPERHERO_ID_EXTRA_KEY: String = "SUPERHERO_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    private fun setup() {
        val superheroId: String = intent.getStringExtra(SUPERHERO_ID_EXTRA_KEY).orEmpty()
        getSuperheroById(superheroId)
    }
    private fun displayErrorMessage() {
        Toast.makeText(this, "Ocurrió un error inesperado :(", Toast.LENGTH_SHORT).show()
    }
    private fun showProgressBar(show: Boolean = true) {
        binding.progressBar.isVisible = show
    }
    private fun fillUI(superheroDetails: SuperheroDetails) {
        binding.tvSuperheroName.text = superheroDetails.name
        fillIvSuperhero(superheroDetails.image)
        fillTvSuperheroFullName(superheroDetails.biography.fullName)
        fillTvPublisher(superheroDetails.biography.publisher)
        fillAllPowerStats(superheroDetails.powerStats)
    }
    private fun fillTvSuperheroFullName(fullName: String) {
        binding.tvSuperheroFullName.text = fullName.ifEmpty {
            binding.tvSuperheroFullName.visibility = View.GONE
            ""
        }
    }
    private fun fillTvPublisher(publisher: String) {
        binding.tvSuperheroPublisher.text = publisher.ifEmpty { "- No específicado -" }
    }
    private fun fillIvSuperhero(image: SuperheroImage) {
        Picasso.get()
            .load(image.url)
            .into(binding.ivSuperhero)
    }
    private fun fillAllPowerStats(powerStats: PowerStats) {
        fillPowerStat(binding.powerStatBarIntelligence, powerStats.intelligence)
        fillPowerStat(binding.powerStatBarStrength, powerStats.power)
        fillPowerStat(binding.powerStatBarSpeed, powerStats.speed)
        fillPowerStat(binding.powerStatBarDurability, powerStats.durability)
        fillPowerStat(binding.powerStatBarPower, powerStats.power)
        fillPowerStat(binding.powerStatBarCombat, powerStats.combat)
    }
    private fun fillPowerStat(powerStatBar: View, powerStat: String) {
        val newLayoutParams = powerStatBar.layoutParams
        newLayoutParams.height = pxToDp(powerStat.toFloat())
        powerStatBar.layoutParams = newLayoutParams
    }
    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }
    private fun getSuperheroById(superheroId: String) {
        showProgressBar()
        ApiRequestHelper.requestFindSuperheroById(superheroId) { response ->
            runOnUiThread {
                if (response.isSuccessful)
                    fillUI(response.body()!!)
                else displayErrorMessage()
                showProgressBar(false)
            }
        }
    }
}