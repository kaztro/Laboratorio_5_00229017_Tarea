package com.example.pokeapiapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapiapp.MyPokemonAdapter
import com.example.pokeapiapp.R
import com.example.pokeapiapp.pojos.Pokemon
import kotlinx.android.synthetic.main.cardview_pokemon.view.*

class PokemonAdapter(var pokemons: List<Pokemon>, val clickListener: (Pokemon) -> Unit) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(),
    MyPokemonAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pokemons.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pokemons[position], clickListener)
    override fun changeDataSet(newDataSet: List<Pokemon>) {
        this.pokemons = newDataSet
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pokemon, clickListener: (Pokemon) -> Unit) = with(itemView) {
            Glide.with(itemView.context)
                .load(item.sprite)
                .placeholder(R.drawable.ic_launcher_background)
                .into(pokemon_sprite_cv)

            pokemon_name_cv.text = item.name
            pokemon_url_cv.text = item.url
            pokemon_fsttype_cv.text = item.fsttype
            pokemon_sndtype_cv.text = item.sndtype
            this.setOnClickListener{ (clickListener(item)) }
        }
    }

}

