package com.example.learningkotlin.superheroapp.services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequestHelper {
    companion object {
        private val retrofit: Retrofit = createRetrofit()
        private fun createRetrofit(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl("https://superheroapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun requestSearchSuperheroes(superheroName: String, handler: (Response<SearchSuperheroesResponse>) -> Unit) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.create(ApiService::class.java).searchSuperheroes(superheroName)
                handler(response)
            }
        }
        fun requestFindSuperheroById(superheroId: String, handler: (Response<SuperheroDetails>) -> Unit) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.create(ApiService::class.java).findSuperheroById(superheroId)
                handler(response)
            }
        }
    }
}