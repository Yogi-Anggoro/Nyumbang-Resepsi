package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.detailpengeluaran

import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.example.bukutamusumbanganresepsi.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class DetailPengeluaranPresenter(var view: DetailPengeluaranContract.View?) : DetailPengeluaranContract.Presenter
{
    init {
        view?.presenter = this
    }
    override fun getPengeluaran(nomorPengeluaran: String?) {
        view?.onProccess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.orderByChild("nomor_pengeluaran").equalTo(nomorPengeluaran)
                .addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val id = data.key
                        val pengeluaran = data.getValue(Pengeluaran::class.java)
                        if (pengeluaran != null){
                            view?.onGetData(pengeluaran,id)
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

    override fun updatePengeluaran(idPengeluaran: String, pengeluaran: Pengeluaran) {
        view?.onProccess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.child(idPengeluaran).setValue(pengeluaran).addOnSuccessListener {
            view?.onSuccess("Success")
            view?.onProccess(false)
        }.addOnFailureListener {
            view?.onError(it.message!!)
            view?.onProccess(false)
        }
    }

    override fun deletePengeluaran(idPengeluaran: String) {
        view?.onProccess(true)
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.child(idPengeluaran).removeValue().addOnCompleteListener {
            view?.onSucessDelete("Success")
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