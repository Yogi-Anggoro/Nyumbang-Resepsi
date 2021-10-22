package com.example.bukutamusumbanganresepsi.fitur.rekap

import com.example.bukutamusumbanganresepsi.base.BasePresenter
import com.example.bukutamusumbanganresepsi.base.BaseView

interface RekapDataContract {

    interface View: BaseView<Presenter>{
        fun onSucesUsser(acara: String , nama: String)
        fun onSuccessDaftarTamu (jumlah: Int)
        fun onSuccessJumlahSumbangan (jumlah: Int)
        fun onSuccessDaftarPengeluaran (jumlah: Int)
        fun onSuccessJumlahPengeluaran (jumlah: Int)
        fun onSuccessJumlahKembalikanSumbangan(jumlah: Int)
        fun onProcess(boolean: Boolean)
        fun onError(message: String)
        fun onJumlah(hasil: Int)

    }

    interface Presenter: BasePresenter{
        fun getUser()
        fun getdaftarTamu()
        fun getJumlahSumbangan()
        fun getDaftarPengeluaran()
        fun getJumlahPengeluaran()
        fun getSumbanganDikembalikan()
        fun  jumlah()
    }
}