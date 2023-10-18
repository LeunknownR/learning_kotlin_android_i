package com.example.learningkotlin.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningkotlin.databinding.ActivitySuperHeroAppBinding
import com.example.learningkotlin.superheroapp.services.ApiRequestHelper
import com.example.learningkotlin.superheroapp.services.SearchSuperheroesResponse
import com.example.learningkotlin.superheroapp.superhero.SuperheroAdapter

class SuperheroAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroAppBinding
    private lateinit var superheroAdapter: SuperheroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    private fun setup() {
        setupSvSuperHero()
        setupRvSuperheroes()
    }
    private fun setupRvSuperheroes() {
        superheroAdapter = SuperheroAdapter { superheroId -> goToSuperheroDetailsActivity(superheroId) }
        binding.rvSuperheroes.layoutManager = LinearLayoutManager(this)
        binding.rvSuperheroes.setHasFixedSize(true)
        binding.rvSuperheroes.adapter = superheroAdapter
    }
    private fun setupSvSuperHero() {
        binding.svSuperhero.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBySuperHeroName(query.orEmpty())
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
    private fun displayErrorMessage() {
        Toast.makeText(this, "No pudo recuperarse la lista de super heroes :(", Toast.LENGTH_SHORT).show()
    }
    private fun showProgressBar(show: Boolean = true) {
        binding.progressBar.isVisible = show
    }
    private fun searchBySuperHeroName(superHeroName: String) {
        showProgressBar()
        ApiRequestHelper.requestSearchSuperheroes(superHeroName) { response ->
            runOnUiThread {
                if (response.isSuccessful)
                    fillRvSuperheroes(response.body())
                else displayErrorMessage()
                showProgressBar(false)
            }
        }
    }
    private fun fillRvSuperheroes(body: SearchSuperheroesResponse?) {
        superheroAdapter.superheroes = body?.superheroes.orEmpty()
    }
    private fun goToSuperheroDetailsActivity(superheroId: String) {
        val intent = Intent(this, SuperheroDetailsActivity::class.java)
        intent.putExtra(SuperheroDetailsActivity.SUPERHERO_ID_EXTRA_KEY, superheroId)
        startActivity(intent)
    }
}