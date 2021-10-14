package com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.detailkembalikansumbangan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.activity_detail_kembalikan_sumbangan.*
import kotlinx.android.synthetic.main.activity_detail_tamu.*

class DetailKembalikanSumbanganActivity : AppCompatActivity(),DetailKembalikanSumbanganContract.View {
    init {
        DetailKembalikanSumbanganPresenter(this)
    }
    override lateinit var presenter : DetailKembalikanSumbanganContract.Presenter
    private var loading : AlertDialog? = null
    var tamu: Tamu? = null
    var nomor : String? = null
    var idTamu : String? = null
    val statussumbangan = "Dikembalikan"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kembalikan_sumbangan)
        loading = showDialogLoading()
        nomor = intent.getStringExtra("nomor")
        presenter.getTamu(nomor)
        btn_addkembalikan_sumbangan_frm.setOnClickListener {
            if (handleInput()) {
                val tamu = Tamu(
                        tv_nomo_ks_frm.text.toString(),
                        tv_Nama_ks_frm.text.toString(),
                        tv_alamat_ks_frm.text.toString(),
                        tv_keterangan_ks_frm.text.toString(),
                        tv_jumlahsumbangan_ks_frm.text.toString(),
                        statussumbangan,
                        et_d_jumlah_sumbangan_frm.text.toString()
                )
                presenter.updateTamu(idTamu!!,tamu)

            }
        }
        }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else {
            loading?.dismiss()
        }
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetData(tamu: Tamu, no: String?) {
        idTamu = no
        tv_nomo_ks_frm.setText(tamu.no)
        tv_Nama_ks_frm.setText(tamu.nama)
        tv_alamat_ks_frm.setText(tamu.alamat)
        tv_keterangan_ks_frm.setText(tamu.ket)
        tv_jumlahsumbangan_ks_frm.setText(tamu.jumlah_sumbangan)
        tv_ststudsumbangan_ks.setText(tamu.Status_sumbangan)
        tv_pengembalian_ks_frm.setText(tamu.pengembalian)

    }

    private fun handleInput() : Boolean {
        if (et_d_jumlah_sumbangan_frm.text.isEmpty()) {
            et_d_jumlah_sumbangan_frm.error = getString(R.string.fill_data)
            et_d_jumlah_sumbangan_frm.requestFocus()
            return false
        }
        return true
    }

    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
}