package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.tanbahpengeluaran

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Pengeluaran

interface AddPengeluaranContract {
    interface View : BaseView<Presenter>{
        fun onError (message: String)
        fun onProccess (boolean: Boolean)
        fun onSuccess(message: String)
        fun onSuccessMail(email: String)
    }
    interface Presenter : BasePresenter {
        fun addPengeluaran (pengeluaran: Pengeluaran)
        fun getUsermail()
    }
}