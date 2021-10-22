package com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.daftarkembalikansumbangan

import android.content.Context
import com.example.bukutamusumbanganresepsi.model.Jadwal
import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class DaftarKembalikanSumbanganPresenter(val context: Context, var view: DaftarKembalikanSumbanganContract.View?): DaftarKembalikanSumbanganContract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getTamu() {
        view?.onProccess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.orderByChild("email_user").equalTo(currentUser.email).
        addValueEventListener(object : ValueEventListener{
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

    override fun getJumlah() {
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val statussumbangan = "Dikembalikan"
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.orderByChild("email_user").equalTo(currentUser.email).
        addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val allTamu: ArrayList<Tamu> = ArrayList()
                if (snapshot.exists()){
                    for (tamuSnap in snapshot.children){
                        val tamu = tamuSnap.getValue(Tamu::class.java)
                        if (tamu != null){
                            if (tamu.Status_sumbangan.equals(statussumbangan)){
                            allTamu.add(tamu)
                            val count: Int = allTamu.count()
                            view?.onSuccessJumlah(count)
                            view?.onProccess(false)
                        }}else{
                            val count: Int = allTamu.count()
                            view?.onSuccessJumlah(count)
                            view?.onProccess(false)
                        }
                    }
                }else{
                    view?.onProccess(false)
                }
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