package com.david_422017001.d_cinema_21.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Checkout (
    var kursi:String ? = "",
    var harga:String ? = ""
) : Parcelable