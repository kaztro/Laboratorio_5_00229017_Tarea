package com.example.pokeapiapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokeapiapp.R
import com.example.pokeapiapp.pojos.Pokemon
import kotlinx.android.synthetic.main.main_content_fragment_layout.view.*

class MainContentFragment: Fragment() {

    var pokemon = Pokemon()

    companion object {
        fun newInstance(pokemon: Pokemon): MainContentFragment {
            val newFragment = MainContentFragment()
            newFragment.pokemon = pokemon
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.main_content_fragment_layout, container, false)

        bindData(view)

        return view
    }

    fun bindData(view: View) {
        view.pokemon_name_main_content_fragment.text = pokemon.name
        view.pokemon_fsttype_main_content_fragment.text = pokemon.fsttype
        view.fsttype_main_content_fragment.text = pokemon.fsttype
        view.sndtype_main_content_fragment.text = pokemon.sndtype
        view.weight_main_content_fragment.text = pokemon.weight
        view.height_main_content_fragment.text = pokemon.height
        view.url_main_content_fragmentll.text = pokemon.url
        Glide.with(view).load(pokemon.sprite)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view.image_main_content_fragment)
    }
}