package com.example.bukutamusumbanganresepsi.fitur.infoakun

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class InfoAkunPresenter(val context: Context, var view: InfoAkunContract.view?) : InfoAkunContract.Presenter {
    init {
        view?.presenter = this
    }

    override fun getUser() {
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var users : ArrayList<User>? = null
                for (dataSnap in snapshot.children){
                    val user = dataSnap.getValue(User::class.java)
                    users?.add(user!!)
                    if (currentUser.email.equals(user?.email)){
                        view?.onSucess(user!!)

                }
            }}

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
            }
        })

    }

    override fun start() {
    }

    override fun destroy() {
        view = null
    }
}