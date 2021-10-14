package com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.detailkembalikansumbangan

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu.AddTamuContract
import com.example.bukutamusumbanganresepsi.model.Tamu

interface DetailKembalikanSumbanganContract {

    interface View : BaseView<Presenter>{
        fun onError (message : String)
        fun onProcess(boolean: Boolean)
        fun onSuccess(message: String)
        fun onGetData (tamu: Tamu, no: String?)
    }

    interface Presenter : BasePresenter {
        fun getTamu(nomorTamu : String?)
        fun updateTamu(idTamu: String, tamu: Tamu)
    }
}