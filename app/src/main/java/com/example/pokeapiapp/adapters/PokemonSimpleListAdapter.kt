package com.example.pokeapiapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapiapp.MyPokemonAdapter
import com.example.pokeapiapp.R
import com.example.pokeapiapp.pojos.Pokemon

class PokemonSimpleListAdapter(var pokemones: List<Pokemon>, val clickListener: (Pokemon)->Unit) : RecyclerView.Adapter<PokemonSimpleListAdapter.ViewHolder>(), MyPokemonAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pokemones.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pokemones[position], clickListener)

    override fun changeDataSet(newDataSet: List<Pokemon>) {
        this.pokemones = newDataSet
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon, clickListener: (Pokemon) -> Unit) = with(itemView){
            title_list_item.text = movie.Title
            genre_list_item.text = movie.Genre
            runtime_list_item.text = movie.Runtime
            this.setOnClickListener { clickListener(movie) }
        }
    }
}