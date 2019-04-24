package com.example.pokeapiapp.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapiapp.AppConstants
import com.example.pokeapiapp.MyPokemonAdapter
import com.example.pokeapiapp.R
import com.example.pokeapiapp.pojos.Pokemon
import java.lang.RuntimeException

class MainListFragment: Fragment() {
    private lateinit var pokemons: ArrayList<Pokemon>
    private lateinit var pokemonesAdapter: MyPokemonAdapter
    var listenerTool: SearchNewPokemonListener? = null

    companion object {
        fun newInstance(dataset: ArrayList<Pokemon>) : MainListFragment{
            val newFragment = MainListFragment()
            newFragment.pokemons = dataset
            return newFragment
        }
    }

    interface SearchNewPokemonListener{
        fun searchPokemon(pokemonName: String)

        fun managePortraitItemClick(pokemon: Pokemon)

        fun manageLandscapeItemClick(pokemon: Pokemon)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.pokemones_list_fragment, container, false)

        if(savedInstanceState != null) pokemons = savedInstanceState.getParcelableArrayList<Pokemon>(AppConstants.MAIN_LIST_KEY)!!

        initRecyclerView(resources.configuration.orientation, view)
        initSearchButton(view)

        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            pokemonesAdapter = PokemonAdapter(pokemons, {pokemon:Pokemon->listenerTool?.managePortraitItemClick(pokemon)})
            container.pokemon_list_rv.adapter = pokemonesAdapter as PokemonAdapter
        }

        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            pokemonesAdapter = PokemonSimpleListAdapter(pokemons, {pokemon:Pokemon->listenerTool?.manageLandscapeItemClick(pokemon)})
            container.pokemon_list_rv.adapter = moviesAdapter as PokemonSimpleListAdapter
        }

        container.pokemon_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun initSearchButton(container: View) = container.add_pokemon_btn.setOnClickListener {
        listenerTool?.searchPokemon(pokemon_name_et.text.toString())
    }

    fun updatePokemonesAdapter(pokemonList: ArrayList<Pokemon>) { pokemonesAdapter.changeDataSet(pokemonList) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is SearchNewPokemonListener) { listenerTool = context }
        else { throw RuntimeException("Se necesita implementar la baskfdasidnawpidnasdap") }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, pokemons)
        super.onSaveInstanceState(outState)
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}

