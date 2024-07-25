package com.example.learningretrofit.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.models.PokemonDetails
import com.example.learningretrofit.models.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Tag

class PokemonRepository (val pokemonService: PokemonService){
    val pokemonsLiveData: MutableLiveData<PokemonList> = MutableLiveData()
    suspend fun getPokemons(limit: Int, offset: Int){

        val result = pokemonService.getPokemons(limit=1000)
        withContext(Dispatchers.Main){

        Log.d("mytag", result.body().toString())
        if (result.body() != null){
            pokemonsLiveData.value = result.body()
        }
        }
    }

}