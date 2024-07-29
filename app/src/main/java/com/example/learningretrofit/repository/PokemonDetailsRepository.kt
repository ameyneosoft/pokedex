package com.example.learningretrofit.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.models.PokemonDetails
import com.example.learningretrofit.models.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PokemonDetailsRepository  (val pokemonService: PokemonService){
    val pokemonDetails : MutableLiveData<PokemonDetails> = MutableLiveData()

    suspend fun getPokemonByName(name : String){
        val result = pokemonService.getPokemon(name)
        withContext(Dispatchers.Main){
                delay(2000)
            Log.d("mytag", result.body().toString())
            if (result.body() != null){
                pokemonDetails.value = result.body()
            }
        }
    }
}