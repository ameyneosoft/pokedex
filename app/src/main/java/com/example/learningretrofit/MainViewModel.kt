package com.example.learningretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningretrofit.models.PokemonList
import com.example.learningretrofit.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (val pokemonRepository: PokemonRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            pokemonRepository.getPokemons(10,10)
        }
    }

    val  pokemons :  MutableLiveData<PokemonList> = pokemonRepository.pokemonsLiveData
}