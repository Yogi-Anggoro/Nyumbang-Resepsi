package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.daftarpengeluaran

import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class Daftar_Pengeluaran_Presenter(var view: Daftar_Pengeluaran_Contract.View?): Daftar_Pengeluaran_Contract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getPengeluaran() {
        view?.onProccess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.orderByChild("email_user").equalTo(currentUser.email).
        addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allPengeluaran: ArrayList<Pengeluaran> = ArrayList()
                for (pengeluaranSnap in snapshot.children) {
                    val pengeluaran = pengeluaranSnap.getValue(Pengeluaran::class.java)
                    if (pengeluaran != null) {
                            allPengeluaran.add(pengeluaran)
                    }
                }
                view?.onSuccess(allPengeluaran)
                view?.onProccess(false)
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProccess(false)
            }

        })
    }

    override fun getJumlah() {
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.orderByChild("email_user").equalTo(currentUser.email).
        addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allPengeluaran: ArrayList<Pengeluaran> = ArrayList()
                if (snapshot.exists()) {
                    for (pengeluaranSnap in snapshot.children) {
                        val pengeluaran = pengeluaranSnap.getValue(Pengeluaran::class.java)
                        if (pengeluaran != null) {
                                allPengeluaran.add(pengeluaran)
                        }
                    }
                }
                val count: Int = allPengeluaran.count()
                view?.onSuccessJumlah(count)
                view?.onProccess(false)
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProccess(false)
            }

        })
    }

override fun getJumlahPengeluaran() {
    val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
    ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var sum = 0
            for (data in snapshot.children) {
                val data1 = data.child("jumlah_pengeluaran").getValue(String::class.java)!!
                if (data1 != null){
                    val Resi = data1.toInt()
                    sum += Resi
                }
            }
            view?.onSuccessJumlahPengeluaran(sum)
            view?.onProccess(false)
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