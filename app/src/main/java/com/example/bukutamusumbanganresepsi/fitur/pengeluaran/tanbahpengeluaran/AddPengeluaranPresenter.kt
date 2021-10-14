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
            view?.onSuccess("Success")
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
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnap in snapshot.children){
                    var users : ArrayList<User>? = null
                    for (dataSnap in snapshot.children){
                        val user = dataSnap.getValue(User::class.java)
                        users?.add(user!!)
                        if (currentUser.email.equals(user?.email)){
                            val email = dataSnap.child("email").getValue()
                            view?.onSuccessMail(email as String)
                            view?.onProccess(false)
                        }
                    }

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