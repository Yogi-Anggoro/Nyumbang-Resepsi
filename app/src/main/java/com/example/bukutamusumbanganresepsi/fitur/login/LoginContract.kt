package com.example.bukutamusumbanganresepsi.fitur.login

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView

interface LoginContract {
    interface View : BaseView<Presenter>{
        fun onError(message: String)
        fun onSucess(message: String)
        fun onProcess(boolean: Boolean)
    }
    interface Presenter : BasePresenter {
        fun login(email: String, pass: String)
    }
}