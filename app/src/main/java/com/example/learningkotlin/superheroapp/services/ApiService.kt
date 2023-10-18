package com.example.learningkotlin.superheroapp.services

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/2206574259548870/search/{name}")
    suspend fun searchSuperheroes(@Path("name") name: String): Response<SearchSuperheroesResponse>
    @GET("/api/2206574259548870/{id}")
    suspend fun findSuperheroById(@Path("id") superheroId: String): Response<SuperheroDetails>
}
data class SearchSuperheroesResponse(
    @SerializedName("results") val superheroes: List<Superhero>
)