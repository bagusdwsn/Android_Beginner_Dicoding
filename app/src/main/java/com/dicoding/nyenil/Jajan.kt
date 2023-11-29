package com.dicoding.nyenil

import android.os.Parcel
import android.os.Parcelable

data class Jajan(
    var name: String?,
    var headline: String?,
    var photo: Int?,
    var description: String?,
    var asal: String?,
    var bahan_dasar: String?,
    var rasa: String?,
    var resep: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(headline)
        parcel.writeValue(photo)
        parcel.writeString(description)
        parcel.writeString(asal)
        parcel.writeString(bahan_dasar)
        parcel.writeString(rasa)
        parcel.writeString(resep)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jajan> {
        override fun createFromParcel(parcel: Parcel): Jajan {
            return Jajan(parcel)
        }

        override fun newArray(size: Int): Array<Jajan?> {
            return arrayOfNulls(size)
        }
    }
}