package com.example.learningretrofit

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.api.RetrofitHelper
import com.example.learningretrofit.databinding.ActivityDetailsBinding
import com.example.learningretrofit.repository.PokemonDetailsRepository

class DetailsActivity : AppCompatActivity() {

    private val binding : ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pokemonName = intent.getStringExtra("pokemonName")?: ""
        val pokemonService = RetrofitHelper.getInstance().create(PokemonService::class.java)
        val pokemonDetailsRepository = PokemonDetailsRepository(pokemonService)
        val detailsViewModel : DetailsViewModel = ViewModelProvider(this,DetailsViewModelFactory(pokemonDetailsRepository,pokemonName)).get(DetailsViewModel::class.java)
        detailsViewModel.pokemonDetails.observe(this){ it ->
            Log.d("meraTag",it.toString())
            binding.pokemonHeightValue.text = it.height.toString()
            binding.pokemonWeightValue.text = it.weight.toString()
            binding.pokemonNameValue.text = it.name
            var type : String=  ""
            it.types?.forEach {it ->
                type += it.type.name +" "
            }
            binding.pokemonTypeValue.text =type
            var abilitiesValue = ""
            it.abilities?.forEach{abilitiesItem ->
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