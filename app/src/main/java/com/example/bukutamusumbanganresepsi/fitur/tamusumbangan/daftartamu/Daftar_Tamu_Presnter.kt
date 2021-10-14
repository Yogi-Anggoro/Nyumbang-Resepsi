package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.daftartamu

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.database.*

class Daftar_Tamu_Presnter(val context: Context, var view : Daftar_Tamu_Contract.View?): Daftar_Tamu_Contract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getTamu() {
        view?.onProcess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.addValueEventListener(object : ValueEventListener{
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

    override fun start() {

    }

    override fun destroy() {
        view = null
    }
}