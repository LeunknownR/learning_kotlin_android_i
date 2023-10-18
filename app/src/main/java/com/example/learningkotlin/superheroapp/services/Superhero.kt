package com.example.learningkotlin.superheroapp.services

import com.google.gson.annotations.SerializedName

data class Superhero(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: SuperheroImage
)
data class SuperheroImage(
    @SerializedName("url") val url: String
)
data class SuperheroDetails(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerStats: PowerStats,
    @SerializedName("image") val image: SuperheroImage,
    @SerializedName("biography") val biography: Biography
)
data class PowerStats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)
data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)