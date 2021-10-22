package com.example.bukutamusumbanganresepsi.fitur.jadwal.detailjadwal

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.Jadwal

interface DetailJadwalContract {

    interface View: BaseView<Presenter>{
        fun onError (message: String?)
        fun onGetData (jadwal: Jadwal, no: String?)
        fun onProccess(boolean: Boolean)
        fun onSuccessDelete(message: String?)
    }

    interface Presenter: BasePresenter{
        fun getJadwal (nomorJadwal: String?)
        fun deleteJadwal(idJadwal: String)
    }

}