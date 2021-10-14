package com.example.bukutamusumbanganresepsi.fitur.main

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.User

interface MainContract {

    interface View : BaseView<Presenter>{
        fun onError (message: String)
        fun onProccess(boolean: Boolean)
        fun onSucess(acara: String , nama: String)
    }

    interface Presenter : BasePresenter{
        fun getUser()
    }
}