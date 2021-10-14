package com.example.bukutamusumbanganresepsi.fitur.register

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.User

interface RegisterContract {

    interface view : BaseView<Presenter>{
        fun onError(message: String)
        fun onSuccess(message: String)
        fun onProcess(message: Boolean)
    }

    interface Presenter : BasePresenter {
        fun register(user: User)
    }
}