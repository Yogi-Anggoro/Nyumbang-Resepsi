package com.example.bukutamusumbanganresepsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Tamu(
    var no: String = "",
    var nama: String = "",
    var alamat: String = "",
    var ket: String = "",
    var jumlah_sumbangan: String = "void",
    var Status_sumbangan: String = "",
    var pengembalian: String = "",
) : Parcelable
