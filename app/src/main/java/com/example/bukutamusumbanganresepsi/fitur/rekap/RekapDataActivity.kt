package com.example.bukutamusumbanganresepsi.fitur.rekap

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bukutamusumbanganresepsi.R
import kotlinx.android.synthetic.main.activity_rekap_data.*


class RekapDataActivity : AppCompatActivity(), RekapDataContract.View {
    override lateinit var presenter: RekapDataContract.Presenter
    private var loading: AlertDialog? = null

    init {
        RekapDataPresenter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekap_data)
        loading = showDialogLoading()
        presenter.getUser()
        presenter.getdaftarTamu()
        presenter.getJumlahSumbangan()
        presenter.getDaftarPengeluaran()
        presenter.getJumlahPengeluaran()
        presenter.getSumbanganDikembalikan()
        presenter.jumlah()


//            val str: String = NumberFormat.getNumberInstance(Locale.US).format(Pendapatan)


    }

    override fun onSucesUsser(acara: String, nama: String) {
        tv_acara_rekap.text = acara
        tv_nama_rekap.text = nama
    }

    override fun onSuccessDaftarTamu(jumlah: Int) {
        tv_jumlah_tamu_rekap.text = jumlah.toString()
        tv_jumlah_kembalikan_tamu.text = jumlah.toString()
    }

    override fun onSuccessJumlahSumbangan(jumlah: Int) {
        tv_jumlah_sumbangan_rekap.text = jumlah.toString()
    }

    override fun onSuccessDaftarPengeluaran(jumlah: Int) {
        tv_jumlah_daftar_rekap.text = jumlah.toString()
    }

    override fun onSuccessJumlahPengeluaran(jumlah: Int) {
        tv_jumlah_pengeluaran_rekap.text = jumlah.toString()
    }

    override fun onSuccessJumlahKembalikanSumbangan(jumlah: Int) {
        tv_sumbkem_rekap.text = jumlah.toString()
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onJumlah(hasil: Int) {
        tv_pendapatan_rekap.text = hasil.toString()
    }


    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }

    private fun penjumlahan() {
        var sumbangan = tv_jumlah_sumbangan_rekap.text.toString().toInt()
        var pengeluaran = tv_jumlah_pengeluaran_rekap.text.toString().toInt()
        var pendapatan = sumbangan-pengeluaran
        tv_pendapatan_rekap.text = pendapatan.toString()
    }


}