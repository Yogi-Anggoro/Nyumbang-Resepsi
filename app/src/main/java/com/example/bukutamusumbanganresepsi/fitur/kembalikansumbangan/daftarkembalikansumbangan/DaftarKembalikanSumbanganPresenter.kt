package com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.daftarkembalikansumbangan

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.database.*

class DaftarKembalikanSumbanganPresenter(val context: Context, var view: DaftarKembalikanSumbanganContract.View?): DaftarKembalikanSumbanganContract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getTamu() {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
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
                view?.onProccess(false)
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onErorr(error.message)
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