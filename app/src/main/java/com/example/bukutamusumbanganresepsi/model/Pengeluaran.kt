package com.example.bukutamusumbanganresepsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pengeluaran(
        var nomor_pengeluaran: String= "",
        var tanggal_pengeluaran: String = "",
        var jumlah_pengeluaran: String = "",
        var nama_pengeluaran: String = "",
        var ket_pengeluaran: String = "",
        var email_user: String = ""
) : Parcelable

