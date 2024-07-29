package com.example.learningretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningretrofit.api.PokemonService
import com.example.learningretrofit.api.RetrofitHelper
import com.example.learningretrofit.databinding.ActivityMainBinding
import com.example.learningretrofit.repository.PokemonRepository

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var gridView: GridView
    private lateinit var pokemonGridAdapter: PokemonGridAdapter

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val pokemonService = RetrofitHelper.getInstance().create(PokemonService::class.java)

        val pokemonRepository = PokemonRepository(pokemonService)
        mainViewModel = ViewModelProvider(
            this, MainViewModelFactory(pokemonRepository)
        )[MainViewModel::class.java]

        gridView = binding.pokemonGridView

        mainViewModel.pokemons.observe(this) {
            val li = it?.results ?: listOf()
            pokemonGridAdapter = PokemonGridAdapter(this, li)
            gridView.adapter = pokemonGridAdapter
        }
        binding.searchText.doAfterTextChanged{
            text ->
                val newLi =mainViewModel.pokemons.value?.results?.filter {
                    val tex = text?: ""
                    it.name.contains(tex,true)
                } ?: listOf()
            pokemonGridAdapter = PokemonGridAdapter(this, newLi)
            gridView.adapter = pokemonGridAdapter
        }
    }
}