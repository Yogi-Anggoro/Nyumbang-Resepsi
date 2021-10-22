package com.example.bukutamusumbanganresepsi.fitur.jadwal.addjadwal

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Jadwal

interface AddJadwalContract {

    interface View: BaseView<Presenter>{
        fun onError (message: String)
        fun onProccess (boolean: Boolean)
        fun onSuccess(message: String)
        fun onSuccessMail(email: String)
    }

    interface Presenter: BasePresenter {
        fun addJadwal (jadwal: Jadwal)
        fun getUserMail()
    }
}