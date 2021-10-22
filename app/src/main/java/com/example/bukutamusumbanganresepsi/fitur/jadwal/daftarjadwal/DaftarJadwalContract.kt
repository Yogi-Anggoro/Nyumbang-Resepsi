package com.example.bukutamusumbanganresepsi.fitur.jadwal.daftarjadwal

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Jadwal

interface DaftarJadwalContract {

    interface View : BaseView<Presenter>{
        fun onError (message: String)
        fun onProccess (boolean: Boolean)
        fun onSuccess (jadwal : ArrayList<Jadwal>)
        fun onSuccessJumlah (jumlah: Int)
    }

    interface Presenter : BasePresenter{
        fun getJadwal()
        fun getJumlah()
    }
}