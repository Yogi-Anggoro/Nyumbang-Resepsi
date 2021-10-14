package com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.daftarkembalikansumbangan

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Tamu

interface DaftarKembalikanSumbanganContract {
    interface View : BaseView<Presenter>{
        fun onErorr(message: String)
        fun onProccess(boolean: Boolean)
        fun onSuccess(tamu: ArrayList<Tamu>)
    }
    interface Presenter: BasePresenter{
        fun getTamu ()
    }
}