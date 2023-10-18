package com.example.learningkotlin.superheroapp.superhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkotlin.R
import com.example.learningkotlin.superheroapp.services.Superhero

class SuperheroAdapter(private val goToSuperheroDetailsActivity: (String) -> Unit) : RecyclerView.Adapter<SuperheroViewHolder>() {
    var superheroes: List<Superhero> = emptyList()
        set(superheroes) {
            field = superheroes
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent,  false)
        return SuperheroViewHolder(view, goToSuperheroDetailsActivity)
    }
    override fun getItemCount(): Int = superheroes.size
    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(superheroes[position])
    }
}