package com.example.learningretrofit

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.api.RetrofitHelper
import com.example.learningretrofit.databinding.ActivityDetailsBinding
import com.example.learningretrofit.repository.PokemonDetailsRepository

class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pokemonName = intent.getStringExtra("pokemonName") ?: ""
        val pokemonService = RetrofitHelper.getInstance().create(PokemonService::class.java)
        val pokemonDetailsRepository = PokemonDetailsRepository(pokemonService)
        val detailsViewModel: DetailsViewModel = ViewModelProvider(
            this, DetailsViewModelFactory(pokemonDetailsRepository, pokemonName)
        ).get(DetailsViewModel::class.java)
        binding.constraintLayout.visibility = View.GONE

        detailsViewModel.pokemonDetails.observe(this) { it ->

            binding.shimmerLayout.shimmerDetailsLayout.visibility = View.GONE
            binding.constraintLayout.visibility = View.VISIBLE
            binding.pokemonHeightValue.text = it.height.toString()
            binding.pokemonWeightValue.text = it.weight.toString()
            binding.pokemonNameValue.text = it.name
            var type: String = ""
            it.types?.forEach { it ->
                type += it.type.name + " "
            }
            binding.pokemonTypeValue.text = type
            var abilitiesValue = ""
            it.abilities?.forEach { abilitiesItem ->
                abilitiesValue += abilitiesItem.ability.name
            }
            binding.pokemonAbilityValue.text = abilitiesValue
            val uri =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it.id}.png"
            Glide.with(this).load(uri).into(binding.pokemonArtwork)
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(it.cries.latest)
            mediaPlayer.prepare()
            binding.playButton.setOnClickListener {
                mediaPlayer.start()
            }
        }


    }
}