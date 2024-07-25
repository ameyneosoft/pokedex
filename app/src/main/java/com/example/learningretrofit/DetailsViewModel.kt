package com.example.learningretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningretrofit.models.PokemonDetails
import com.example.learningretrofit.repository.PokemonDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel (pokemonDetailsRepository: PokemonDetailsRepository,pokemonName : String): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
        pokemonDetailsRepository.getPokemonByName(pokemonName)
        }
    }
    var pokemonDetails : MutableLiveData<PokemonDetails> = pokemonDetailsRepository.pokemonDetails
}