package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.daftarpengeluaran

import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class Daftar_Pengeluaran_Presenter(var view: Daftar_Pengeluaran_Contract.View?): Daftar_Pengeluaran_Contract.Presenter {
    init {
        view?.presenter = this
    }
    override fun getPengeluaran() {
        view?.onProccess(true)
        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allPengeluaran: ArrayList<Pengeluaran> = ArrayList()
                for (pengeluaranSnap in snapshot.children) {
                    val pengeluaran = pengeluaranSnap.getValue(Pengeluaran::class.java)
                    if (pengeluaran != null) {
                        if (currentUser.email.equals(pengeluaran?.email_user)) {
                            allPengeluaran.add(pengeluaran)
                            view?.onSuccess(allPengeluaran)
                            view?.onProccess(false)
                        } else {
                            view?.onProccess(false)
                        }
                    } else {
                        view?.onError("Data Kosong")
                        view?.onProccess(false)
                    }
                }
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
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allPengeluaran: ArrayList<Pengeluaran> = ArrayList()
                if (snapshot.exists()) {
                    for (pengeluaranSnap in snapshot.children) {
                        val pengeluaran = pengeluaranSnap.getValue(Pengeluaran::class.java)
                        if (pengeluaran != null) {
                            if (currentUser.email.equals(pengeluaran?.email_user)) {
                                allPengeluaran.add(pengeluaran)
                                val count: Int = allPengeluaran.count()
                                view?.onSuccessJumlah(count)
                                view?.onProccess(false)
                            } else {
                                view?.onProccess(false)
                            }
                        }
                    }
                } else {
                    view?.onProccess(false)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view?.onError(error.message)
                view?.onProccess(false)
            }

        })
    }

//    override fun getJumlahSumbangan() {
//        val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
//        val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val allpengeluaran :  ArrayList<String> = ArrayList()
//                if (snapshot.exists()){
//                    for (pengeluaranjumlah in snapshot.children){
//                        val jumlahpengeluaran = pengeluaranjumlah.child("jumlah_pengeluaran").getValue()
//                        val pengeluaran = pengeluaranjumlah.getValue(Pengeluaran::class.java)
//                        if (jumlahpengeluaran != null){
//                            if (currentUser.email.equals(pengeluaran?.email_user)) {
//                                allpengeluaran.add(jumlahpengeluaran.toString())
//                                val jumlah: Int = allpengeluaran.count()
//                                view?.onSuccessJumlahSumbangan(jumlah)
//                                view?.onProccess(false)
//                            }
//                        }
//                    }
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                view?.onError(error.message)
//                view?.onProccess(false)
//            }
//
//        })
//
//    }
    //versigood
//override fun getJumlahSumbangan() {
//    val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
//    val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
//    ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object : ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            val allpengeluaranz :  ArrayList<String> = ArrayList()
//            for (dataSnap in snapshot.children){
//                    val pengeluaran = dataSnap.getValue(Pengeluaran::class.java)
//                    if (currentUser.email.equals(pengeluaran?.email_user)){
//                        val email = dataSnap.child("jumlah_pengeluaran").getValue()
//                        allpengeluaranz.add(email.toString())
//                        val jumlahh: Int = allpengeluaranz!!.count()
//                        view?.onSuccessJumlahSumbangan(jumlahh)
//                        view?.onProccess(false)
//                    }
//            }
//
//        }
//        override fun onCancelled(error: DatabaseError) {
//            view?.onError(error.message)
//            view?.onProccess(false)
//        }
//
//    })
//
//}
override fun getJumlahSumbangan() {
    val currentUser : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference("Pengeluaran")
    ref.orderByChild("email_user").equalTo(currentUser.email).addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            var sum = 0
            for (data in snapshot.children) {
                val data1 = data.child("jumlah_pengeluaran").getValue(String::class.java)!!
                val Resi = data1.toInt()
                sum += Resi
                view?.onSuccessJumlahSumbangan(sum)
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