package com.example.pokeapiapp.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.pokeapiapp.R
import com.example.pokeapiapp.pojos.Pokemon

class PokemonVIewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_pokemon)

        setSupportActionBar(toolbarviewer)
        collapsingtoolbarviewer.setExpandedTitleTextAppearence(R.style.ExpandedAppBar)
        collpasingtoolbarviewer.setCollapsedTitleTextAppearence(R.style.CollapsedAppBar)

        val reciever: Pokemon = intent?.extras?.getParcelable("POKEMON")?: Pokemon()
        init(reciever)
    }

    fun init(pokemon: Pokemon) {
        Glide.with(this)
            .load(pokemon.sprite)
            .placeholder(R.drawable.ic_launcher_background)
            .into(app_bar_image_viewer)
        collapsingtoolbarviewer.name = pokemon.name
        app_bar_
    }
}