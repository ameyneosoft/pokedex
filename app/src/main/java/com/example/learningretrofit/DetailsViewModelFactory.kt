package com.example.learningretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learningretrofit.repository.PokemonDetailsRepository

class DetailsViewModelFactory(val pokemonDetailsRepository: PokemonDetailsRepository,val pokemonName: String) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(pokemonDetailsRepository,pokemonName) as T
    }
}