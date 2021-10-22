package com.example.bukutamusumbanganresepsi.fitur.jadwal.daftarjadwal

import com.example.bukutamusumbanganresepsi.model.Jadwal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class DaftarJadwalPresenter(var view: DaftarJadwalContract.View?): DaftarJadwalContract.Presenter {

    init {
        view?.presenter = this
    }
    override fun getJadwal() {
        view?.onProccess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Jadwal")
        ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val allJadwal: ArrayList<Jadwal> = ArrayList()
                for (jadwalSnap in snapshot.children){
                    val jadwal = jadwalSnap.getValue(Jadwal::class.java)
                    if (jadwal != null){
                        allJadwal.add(jadwal)
                    }
                }
                view?.onSuccess(allJadwal)
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
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Jadwal")
        ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val allJadwal: ArrayList<Jadwal> = ArrayList()
                if (snapshot.exists()){
                    for (jadwalSnap in snapshot.children){
                        val jadwal = jadwalSnap.getValue(Jadwal::class.java)
                        if (jadwal != null){
                            allJadwal.add(jadwal)
                            val count: Int = allJadwal.count()
                            view?.onSuccessJumlah(count)
                            view?.onProccess(false)
                        }else{
                            val count: Int = allJadwal.count()
                            view?.onSuccessJumlah(count)
                            view?.onProccess(false)
                        }
                    }
                }else{
                    view?.onSuccessJumlah(0)
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