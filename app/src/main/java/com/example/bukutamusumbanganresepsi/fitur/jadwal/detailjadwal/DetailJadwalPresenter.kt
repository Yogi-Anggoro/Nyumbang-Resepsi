package com.example.bukutamusumbanganresepsi.fitur.jadwal.detailjadwal

import com.example.bukutamusumbanganresepsi.model.Jadwal
import com.google.firebase.database.*

class DetailJadwalPresenter(var view: DetailJadwalContract.View?): DetailJadwalContract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getJadwal(nomorJadwal: String?) {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Jadwal")
        ref.orderByChild("nama").equalTo(nomorJadwal).
        addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val id = data.key
                        val jadwal = data.getValue(Jadwal::class.java)
                        if (jadwal != null){
                            view?.onGetData(jadwal,id)
                            view?.onProccess(false)
                        }else{
                            view?.onError("Data Tidak Ditemukan")
                            view?.onProccess(false)
                        }
                    }
                }else{
                    view?.onError("Data Tidak Ditemukan")
                    view?.onProccess(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProccess(false)
            }
        })
    }

    override fun deleteJadwal(idJadwal: String) {
        view?.onProccess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("Jadwal")
        ref.child(idJadwal).removeValue().addOnCompleteListener {
            view?.onSuccessDelete("Jadwal Telah Selesai")
            view?.onProccess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProccess(false)
        }
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }
}