package com.example.learningretrofit.models

data class PokemonList(val next: String = "",
                       val previous: String = "",
                       val count: Int = 0,
                       val results: List<Pokemon>?)