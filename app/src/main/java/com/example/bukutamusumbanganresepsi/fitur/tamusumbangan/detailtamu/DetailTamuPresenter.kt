package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.detailtamu

import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.database.*

class DetailTamuPresenter(var view: DetailTamuContract.View?): DetailTamuContract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getTamu(nomorTamu: String?) {
        view?.onProcess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.orderByChild("no").equalTo(nomorTamu)
                .addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            for (data in snapshot.children){
                                val id = data.key
                                val tamu = data.getValue(Tamu::class.java)
                                if (tamu != null){
                                    view?.onGetData(tamu, id)
                                    view?.onProcess(false)
                                }else{
                                    view?.onError("Data tak ditemukan")
                                    view?.onProcess(false)
                                }
                            }

                        }else{
                            view?.onError("Data tak ditemukan")
                            view?.onProcess(false)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        view?.onError(error.message)
                        view?.onProcess(false)
                    }
                })
    }

    override fun updateTamu(idTamu: String, tamu: Tamu) {
        view?.onProcess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.child(idTamu).setValue(tamu).addOnCompleteListener {
            view?.onSuccess("Success")
            view?.onProcess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProcess(false)
        }

    }

    override fun deleteTamu(idTamu: String) {
        view?.onProcess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.child(idTamu).removeValue().addOnSuccessListener {
            view?.onSucessDelete("Success")
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