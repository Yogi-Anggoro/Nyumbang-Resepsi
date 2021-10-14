package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.daftartamu

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Tamu

interface Daftar_Tamu_Contract {

    interface View : BaseView<Presenter>{
        fun onError (message: String)
        fun onSuccess (tamu: ArrayList<Tamu>)
        fun onProcess (boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getTamu()
    }
}