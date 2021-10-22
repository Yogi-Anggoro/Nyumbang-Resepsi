package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.daftartamu

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class Daftar_Tamu_Presnter(val context: Context, var view : Daftar_Tamu_Contract.View?): Daftar_Tamu_Contract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getTamu() {
        view?.onProcess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.orderByChild("email_user").equalTo(currentUser.email)
            .addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val allTamu : ArrayList<Tamu> = ArrayList()
                for (tamuSnap in snapshot.children){
                    val tamu = tamuSnap.getValue(Tamu::class.java)
                    if (tamu != null){
                        allTamu.add(tamu)
                    }
                }
                view?.onSuccess(allTamu)
                view?.onProcess(false)
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }
        })
    }

    override fun getJumlahTamu() {
        view?.onProcess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.orderByChild("email_user").equalTo(currentUser.email).
        addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allTamu: ArrayList<Tamu> = ArrayList()
                if (snapshot.exists()) {
                    for (TamuSnap in snapshot.children) {
                        val tamu = TamuSnap.getValue(Tamu::class.java)
                        if (tamu != null) {
                                allTamu.add(tamu)
                        }
                    }
                }
                val count: Int = allTamu.count()
                view?.onSuccessJumlah(count)
                view?.onProcess(false)
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