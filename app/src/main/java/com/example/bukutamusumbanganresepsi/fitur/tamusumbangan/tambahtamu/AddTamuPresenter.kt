package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu

import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AddTamuPresenter(var view: AddTamuContract.view?): AddTamuContract.presenter {
    init {
        view?.presenter = this
    }
    override fun addTamu(tamu: Tamu) {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.push().setValue(tamu).addOnCompleteListener {
            view?.onSuccess("Tamu Berhasil Ditambahkan")
            view?.onProcess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProcess(false)
        }

    }

    override fun getUsermail() {
        view?.onProcess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref.orderByChild("email").equalTo(currentUser.email).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnap in snapshot.children){
                    val email = dataSnap.child("email").getValue()
                    view?.onSuccessMail(email as String)
                    view?.onProcess(false)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }
}