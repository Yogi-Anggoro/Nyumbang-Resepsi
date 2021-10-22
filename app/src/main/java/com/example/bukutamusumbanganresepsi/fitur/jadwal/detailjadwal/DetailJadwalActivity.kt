package com.example.bukutamusumbanganresepsi.fitur.jadwal.detailjadwal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Jadwal
import kotlinx.android.synthetic.main.activity_detail_jadwal.*

class DetailJadwalActivity : AppCompatActivity(), DetailJadwalContract.View {
    init {
        DetailJadwalPresenter(this)
    }
    override lateinit var presenter: DetailJadwalContract.Presenter
    private var loading: AlertDialog? = null
    private var nama: String? = null
    private var idJadwal: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jadwal)
        loading = showDialogLoading()
        nama = intent.getStringExtra("nama")
        presenter.getJadwal(nama)
        btn_selesai_daftar.setOnClickListener {
            presenter.deleteJadwal(idJadwal!!)
        }
        btn_kembali_daftar.setOnClickListener {
            finish()
        }
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetData(jadwal: Jadwal, nama: String?) {
        idJadwal = nama
        tv_acara_jadwal_detail.setText(jadwal.acara)
        tv_nama_jadwal_detail.setText(jadwal.nama)
        tv_alamat_jadwal_detail.setText(jadwal.nama)
        tv_tanggal_detail.setText(jadwal.tanggal)
    }

    override fun onProccess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    override fun onSuccessDelete(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }
    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }


}