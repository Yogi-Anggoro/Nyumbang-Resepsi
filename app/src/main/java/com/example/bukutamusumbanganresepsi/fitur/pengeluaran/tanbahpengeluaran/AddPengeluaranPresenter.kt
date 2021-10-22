package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.tanbahpengeluaran

import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.example.bukutamusumbanganresepsi.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AddPengeluaranPresenter (var view : AddPengeluaranContract.View?) : AddPengeluaranContract.Presenter {
    init {
        view?.presenter = this
    }
    override fun addPengeluaran(pengeluaran: Pengeluaran) {
        view?.onProccess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.push().setValue(pengeluaran).addOnCompleteListener {
            view?.onSuccess("Data Pengeluaran Telah Ditambahkan")
            view?.onProccess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProccess(false)
        }
    }

    override fun getUsermail() {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref.orderByChild("email").equalTo(currentUser.email).addValueEventListener(object: ValueEventListener {
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