package com.example.bukutamusumbanganresepsi.fitur.rekap

import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.example.bukutamusumbanganresepsi.model.Tamu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class RekapDataPresenter(var view: RekapDataContract.View?): RekapDataContract.Presenter {
    init {
        view?.presenter = this
    }
    var penambah: Int = 0
    var pengurang: Int = 0
    override fun getUser() {
        view?.onProcess(true)
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        ref.orderByChild("email").equalTo(currentUser.email)
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children){
                        val acara = dataSnap.child("acara").getValue()
                        val nama = dataSnap.child("nama").getValue()
                        view?.onSucesUsser(acara as String, nama as String)
                        view?.onProcess(false)

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    view?.onError(error.message)
                    view?.onProcess(false)
                }
            })
    }

    override fun getdaftarTamu() {
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
                            val count: Int = allTamu.count()
                            view?.onSuccessDaftarTamu(count)
                            view?.onProcess(false)
                        }else{
                            view?.onProcess(false)
                        }
                    }
                } else {
                    view?.onProcess(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

    override fun getJumlahSumbangan() {
        view?.onProcess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Tamu")
        ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sum = 0
                for (data in snapshot.children) {
                    val data1 = data.child("jumlah_sumbangan").getValue(String::class.java)!!
                    val Resi = data1.toInt()
                    sum += Resi
                    view?.onSuccessJumlahSumbangan(sum)
                    view?.onProcess(false)
                    penambah=sum
                    jumlah()

                }

            }
            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

    override fun getDaftarPengeluaran() {
        view?.onProcess(true)
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
                            val count: Int = allPengeluaran.count()
                            view?.onSuccessDaftarPengeluaran(count)
                            view?.onProcess(false)
                        }else {
                            val count: Int = allPengeluaran.count()
                            view?.onSuccessDaftarPengeluaran(count)
                            view?.onProcess(false)
                        }
                    }
                } else {
                    view?.onProcess(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

    override fun getJumlahPengeluaran() {
        view?.onProcess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sum = 0
                for (data in snapshot.children) {
                    val data1 = data.child("jumlah_pengeluaran").getValue(String::class.java)!!
                    val Resi = data1.toInt()
                    sum += Resi
                    view?.onSuccessJumlahPengeluaran(sum)
                    view?.onProcess(false)
                    pengurang=sum
                    jumlah()
                }

            }
            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

    override fun getSumbanganDikembalikan() {
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
                                view?.onSuccessJumlahKembalikanSumbangan(count)
                                view?.onProcess(false)
                            }}else{
                            val count: Int = allTamu.count()
                            view?.onSuccessJumlahKembalikanSumbangan(count)
                            view?.onProcess(false)
                        }
                    }
                }else{
                    view?.onProcess(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProcess(false)
            }

        })
    }

    override fun jumlah() {
        val satu = penambah
        val dua = pengurang
        val hasil = satu-dua
        view?.onJumlah(hasil)
        view?.onProcess(false)
    }

    override fun start() {

    }

    override fun destroy() {
        view = null
    }



}