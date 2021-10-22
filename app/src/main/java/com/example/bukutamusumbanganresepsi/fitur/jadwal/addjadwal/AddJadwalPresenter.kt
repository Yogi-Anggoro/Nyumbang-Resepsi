package com.example.bukutamusumbanganresepsi.fitur.jadwal.addjadwal

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.Jadwal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AddJadwalPresenter(var view: AddJadwalContract.View?): AddJadwalContract.Presenter {
    init {
        view?.presenter = this
    }
    override fun addJadwal(jadwal: Jadwal) {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Jadwal")
        ref.push().setValue(jadwal).addOnCompleteListener {
            view?.onSuccess("Jadwal Telah Ditambahkan")
            view?.onProccess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProccess(false)
        }
    }

    override fun getUserMail() {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref.orderByChild("email").equalTo(currentUser.email).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnap in snapshot.children){
                    val email = dataSnap.child("email").getValue()
                    view?.onSuccessMail(email as String)
                    view?.onProccess(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProccess(false)
            }

        })

    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }
}