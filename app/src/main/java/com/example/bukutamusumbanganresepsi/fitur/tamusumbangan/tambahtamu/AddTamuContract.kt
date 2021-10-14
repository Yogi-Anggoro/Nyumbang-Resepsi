package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Tamu

interface AddTamuContract {
    interface view : BaseView<presenter>{
        fun onError(message: String)
        fun onSuccess(message: String)
        fun onProcess(boolean: Boolean)
    }
    interface presenter : BasePresenter{
        fun addTamu(tamu: Tamu)
    }
}