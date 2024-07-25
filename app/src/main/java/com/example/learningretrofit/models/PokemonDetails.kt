package com.example.learningretrofit.models

data class PokemonDetails(val abilities: List<AbilitiesItem>?,
                          val cries: Cries,
                          val types: List<TypesItem>?,
                          val name: String = "",
                          val weight: Int = 0,
                          val id: Int = 0,
                          val sprites: Sprites,
                          val height: Int = 0)