package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.detailpengeluaran

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Pengeluaran

interface DetailPengeluaranContract {

    interface View : BaseView<Presenter>{
        fun onError (message: String?)
        fun onGetData (pengeluaran: Pengeluaran , no: String?)
        fun onProccess (boolean: Boolean)
        fun onSuccess (message: String?)
        fun onSucessDelete(message: String?)
    }

    interface Presenter : BasePresenter{
        fun getPengeluaran (nomorPengeluaran: String?)
        fun updatePengeluaran (idPengeluaran: String, pengeluaran: Pengeluaran)
        fun deletePengeluaran (idPengeluaran: String)
    }
}