package com.example.learningkotlin.superheroapp.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.databinding.ItemSuperheroBinding
import com.example.learningkotlin.superheroapp.services.Superhero
import com.example.learningkotlin.superheroapp.services.SuperheroImage
import com.squareup.picasso.Picasso

class SuperheroViewHolder: RecyclerView.ViewHolder {
    private var binding: ItemSuperheroBinding
    private val goToSuperheroDetailsActivity: (String) -> Unit
    constructor(view: View, goToSuperheroDetailsActivity: (String) -> Unit): super(view) {
        binding = ItemSuperheroBinding.bind(view)
        this.goToSuperheroDetailsActivity = goToSuperheroDetailsActivity
    }
    fun render(superhero: Superhero) {
        binding.tvSuperheroName.text = superhero.name
        binding.root.setOnClickListener {
            goToSuperheroDetailsActivity(superhero.id)
        }
        fillImage(superhero.image)
    }
    private fun fillImage(image: SuperheroImage) {
        Picasso.get()
            .load(image.url)
            .into(binding.ivSuperhero)
    }
}