package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.daftarpengeluaran

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Pengeluaran

interface Daftar_Pengeluaran_Contract {

    interface View : BaseView<Presenter>{
        fun onError (message: String)
        fun onProccess (boolean: Boolean)
        fun onSuccess (pengeluaran : ArrayList<Pengeluaran>)
        fun onSuccessJumlah (jumlah: Int)
        fun onSuccessJumlahSumbangan (jumlahs: Int)
    }

    interface Presenter : BasePresenter{
        fun getPengeluaran ()
        fun getJumlah()
        fun getJumlahSumbangan()
    }
}