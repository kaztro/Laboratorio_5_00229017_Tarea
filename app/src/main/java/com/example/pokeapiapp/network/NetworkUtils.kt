package com.example.pokeapiapp.network

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val POKEMONS_API_BASEURL = "https://pokeapi.co/api/v2/"

    fun buildtSearchUrl(pokeName: String) : URL {
        val builtUri = Uri.parse(POKEMONS_API_BASEURL)
            .buildUpon()
            .appendQueryParameter("t",pokeName)
            .build()
        return try{ URL(builtUri.toString()) }
               catch (e : MalformedURLException) { URL("") }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL) : String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if(hasInput) { scanner.next() } else { "" }
        } finally { urlConnection.disconnect() }
    }
}