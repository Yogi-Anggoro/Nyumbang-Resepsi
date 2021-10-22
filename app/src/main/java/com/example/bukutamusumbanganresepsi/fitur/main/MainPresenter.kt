package com.example.bukutamusumbanganresepsi.fitur.main

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MainPresenter(val context: Context, var view: MainContract.View?): MainContract.Presenter {

    init {
        view?.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }

    override fun getUser() {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref.orderByChild("email").equalTo(currentUser.email)
            .addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnap in snapshot.children){
                            val acara = dataSnap.child("acara").getValue()
                            val nama = dataSnap.child("nama").getValue()
                            view?.onSucess(acara as String, nama as String)
                            view?.onProccess(false)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProccess(false)
            }
        })
    }
}