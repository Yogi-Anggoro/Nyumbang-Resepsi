package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.detailtamu

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Tamu

interface DetailTamuContract {
    interface View : BaseView<Presenter>{
        fun onError(message: String)
        fun onGetData(tamu: Tamu, no: String?)
        fun onSucessDelete(message: String)
        fun onSuccess(message: String)
        fun onProcess(boolean: Boolean)
    }
    interface Presenter : BasePresenter{
        fun getTamu(nomorTamu: String?)
        fun updateTamu(idTamu: String, tamu: Tamu)
        fun deleteTamu(idTamu: String)
    }
}