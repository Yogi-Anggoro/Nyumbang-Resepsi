package com.example.bukutamusumbanganresepsi.fitur.infoakun

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView
import com.example.bukutamusumbanganresepsi.model.User

interface InfoAkunContract {
    interface view : BaseView<Presenter>{
        fun onError(message: String)
        fun onSucess(user: User)
        fun onProcess(boolean: Boolean)
    }
    interface Presenter : BasePresenter{
        fun getUser()
    }
}