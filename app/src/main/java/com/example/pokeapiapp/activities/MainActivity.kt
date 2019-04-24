package com.example.pokeapiapp.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pokeapiapp.AppConstants
import com.example.pokeapiapp.R
import com.example.pokeapiapp.fragments.MainContentFragment
import com.example.pokeapiapp.fragments.MainListFragment
import com.example.pokeapiapp.network.NetworkUtils
import com.example.pokeapiapp.pojos.Pokemon
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), MainListFragment.SearchNewPokemonListener {

    private lateinit var mainFragment: MainListFragment
    private lateinit var mainCompatActivity: MainContentFragment
    private var pokemonList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonList = savedInstanceState?.getParcelableArrayList(AppConstants.dataset_saveinstance_key)?: ArrayList()

        initMainFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.dataset_saveinstance_key, pokemonList)
        super.onSaveInstanceState(outState)
    }

    fun initMainFragment() {
        mainFragment = MainListFragment.newInstance(pokemonList)

        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        else{
            mainContentFragment = MainContentFragment.newInstance(Pokemon())
            changeFragment(R.id.land_main_cont_fragment, mainContentFragment)

            R.id.land_main_fragment
        }
        changeFragment(resource, mainFragment)
    }

    fun addPokemonToList(pokemon: Pokemon) {
        pokemonList.add(pokemon)
        mainFragment.updatePokemonesAdapter(pokemonList)
        Log.d("Number", pokemonList.size.toString())
    }

    override fun searchPokemon(pokemonName: String) { FetchPokemon().execute(pokemonName) }

    override fun managePortraitItemClick(pokemon: Pokemon) {
        val pokemonBundle = Bundle()
        pokemonBundle.putParcelable("POKEMON", pokemon)
        startActivity(Intent(this, PokemonViewerActivity::class.java).putExtras(pokemonBundle))
    }

    private fun changeFragment(id: Int, frag: Fragment) { supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun manageLandscapeItemClick(pokemon: Pokemon) {
        mainContentFragment = MainContentFragment.newInstance(pokemon)
        changeFragment(R.id.land_main_cont_fragment, mainContentFragment)
    }

    private inner class FetchPokemon : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            if(params.isNullOrEmpty()) return ""

            val pokemonName = params[0]
            val pokemonUrl = NetworkUtils().buildtSearchUrl(pokemonName)

            return try { NetworkUtils().getResponseFromHttpUrl(pokemonUrl) } catch (e: IOException) { "" }
        }

        override fun onPostExecute(pokemonInfo: String?) {
            super.onPostExecute(pokemonInfo)
            if (!pokemonInfo.isEmpty()) {
                val pokemonJson = JSONObject(pokemonInfo)
                if (pokemonJson.getString("Response") == "True") {
                    val pokemon = Gson().fromJson<Pokemon>(pokemonInfo, Pokemon::class.java)
                    addPokemonToList(pokemon)
                } else {
                    Toast.makeText(this@MainActivity, "No existe eso man, ", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "La regue xd, ", Toast.LENGTH_LONG).show()
            }
        }
    }
}
