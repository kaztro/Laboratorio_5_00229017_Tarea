package com.example.pokeapiapp

import com.example.pokeapiapp.pojos.Pokemon

object AppConstants{
    val dataset_saveinstance_key = "CLE"
    val MAIN_LIST_KEY = "key_list_pokemons"
}

interface MyPokemonAdapter {
    fun changeDataSet(newDataSet: List<Pokemon>)
}
