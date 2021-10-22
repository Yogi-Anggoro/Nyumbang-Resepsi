package com.example.bukutamusumbanganresepsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jadwal(
    var email_user: String ="",
    var acara: String = "",
    val nama: String = "",
    val alamat: String = "",
    var tanggal: String = ""

): Parcelable
