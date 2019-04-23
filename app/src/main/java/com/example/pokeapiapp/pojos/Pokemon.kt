package com.example.pokeapiapp.pojos

import android.os.Parcel
import android.os.Parcelable

data class Pokemon (
    val name: String = "N/A",
    val fsttype: String = "N/A",
    val sndtype: String = "N/A",
    val weight: String = "N/A",
    val height: String = "N/A",
    val url:String = "N/A",
    val sprite:String = "N/A"
) : Parcelable {

    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        fsttype = parcel.readString(),
        sndtype = parcel.readString(),
        weight = parcel.readString(),
        height = parcel.readString(),
        url = parcel.readString(),
        sprite = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(fsttype)
        parcel.writeString(sndtype)
        parcel.writeString(weight)
        parcel.writeString(height)
        parcel.writeString(url)
        parcel.writeString(sprite)

    }

    override fun describeContents() = 0

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Pokemon> {
            override fun createFromParcel(parcel: Parcel): Pokemon = Pokemon(parcel)
            override fun newArray(size: Int): Array<Pokemon?> = arrayOfNulls(size)
        }
    }
}