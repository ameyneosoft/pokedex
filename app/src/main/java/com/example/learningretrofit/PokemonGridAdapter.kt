package com.example.learningretrofit

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.learningretrofit.models.Pokemon
import com.example.learningretrofit.models.PokemonList
import com.google.android.material.imageview.ShapeableImageView

class PokemonGridAdapter(context: Context, var li: List<Pokemon>) :
    ArrayAdapter<Pokemon?>(context, 0, li) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_view, parent, false)
        val imageView = view?.findViewById<ShapeableImageView>(R.id.pokemonImage)
        val textView = view?.findViewById<TextView>(R.id.pokemonName)
        val splitUri = li.get(position)?.url.toString().split("/")
        val pos = splitUri[splitUri.size - 2].toInt()
        val ur =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pos}.png"
        Glide.with(context).load(ur).into(imageView!!)
        val text = li[position].name.substring(0, 1).uppercase() + li[position].name.substring(1)
        textView!!.text = text

        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND,null,context,DetailsActivity::class.java)
            intent.putExtra("pokemonName",text.lowercase())
            context.startActivity(intent)
        }

        return view
    }
}