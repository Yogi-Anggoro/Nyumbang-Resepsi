package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu

import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddTamuPresenter(var view: AddTamuContract.view?): AddTamuContract.presenter {
    init {
        view?.presenter = this
    }
    override fun addTamu(tamu: Tamu) {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.push().setValue(tamu).addOnCompleteListener {
            view?.onSuccess("Success")
            view?.onProcess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProcess(false)
        }

    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }
}