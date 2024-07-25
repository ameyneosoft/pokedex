package com.example.learningretrofit.api

import com.example.learningretrofit.models.Pokemon
import com.example.learningretrofit.models.PokemonDetails
import com.example.learningretrofit.models.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit : Int): Response<PokemonList>

    @GET("pokemon/" +"{pokemon}")
    suspend fun getPokemon(@Path("pokemon")  pokemon : String) : Response<PokemonDetails>
}