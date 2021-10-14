package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.detailpengeluaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import kotlinx.android.synthetic.main.activity_detail_pengeluaran.*

class DetailPengeluaranActivity : AppCompatActivity(), DetailPengeluaranContract.View {

    override lateinit var presenter: DetailPengeluaranContract.Presenter
    private var loading : AlertDialog? = null
    private var nomor : String? = null
    private var idPengeluaran : String? = null

    init {
        DetailPengeluaranPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pengeluaran)
        loading = showDialogLoading()
        nomor = intent.getStringExtra("nomor")
        presenter.getPengeluaran(nomor)

        btn_updatepengeluaran.setOnClickListener {
            if (handleInput()){
                val pengeluaran = Pengeluaran(
                        et_nomor_pengeluaran_detail.text.toString(),
                        et_tanggal_pengeluaran_detail.text.toString(),
                        et_jumlah_pengeluaran_detail.text.toString(),
                        et_nama_pengeluaran_detail.text.toString(),
                        et_keterangan_pengeluaran_detail.text.toString(),
                        tv_getemailuser_detailpengeluaran.text.toString()
                )
                presenter.updatePengeluaran(idPengeluaran!!,pengeluaran)
            }
        }

        btn_deletepengeluaran.setOnClickListener {
            presenter.deletePengeluaran(idPengeluaran!!)
        }

    }
    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetData(pengeluaran: Pengeluaran, no: String?) {
        idPengeluaran = no
        et_nomor_pengeluaran_detail.setText(pengeluaran.nomor_pengeluaran)
        et_tanggal_pengeluaran_detail.setText(pengeluaran.tanggal_pengeluaran)
        et_jumlah_pengeluaran_detail.setText(pengeluaran.jumlah_pengeluaran)
        et_nama_pengeluaran_detail.setText(pengeluaran.nama_pengeluaran)
        et_keterangan_pengeluaran_detail.setText(pengeluaran.ket_pengeluaran)
        tv_getemailuser_detailpengeluaran.setText(pengeluaran.email_user)
    }

    override fun onProccess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    override fun onSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSucessDelete(message: String?) {
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

    private fun handleInput() : Boolean{
        if (et_nomor_pengeluaran_detail.text.isEmpty()) {
            et_nomor_pengeluaran_detail.error = getString(R.string.fill_data)
            et_nomor_pengeluaran_detail.requestFocus()
            return false
        }
        if (et_tanggal_pengeluaran_detail.text.isEmpty()) {
            et_tanggal_pengeluaran_detail.error = getString(R.string.fill_data)
            et_tanggal_pengeluaran_detail.requestFocus()
            return false
        }
        if (et_jumlah_pengeluaran_detail.text.isEmpty()) {
            et_jumlah_pengeluaran_detail.error = getString(R.string.fill_data)
            et_jumlah_pengeluaran_detail.requestFocus()
            return false
        }
        if (et_nama_pengeluaran_detail.text.isEmpty()) {
            et_nama_pengeluaran_detail.error = getString(R.string.fill_data)
            et_nama_pengeluaran_detail.requestFocus()
            return false
        }
        if (et_keterangan_pengeluaran_detail.text.isEmpty()) {
            et_keterangan_pengeluaran_detail.error = getString(R.string.fill_data)
            et_keterangan_pengeluaran_detail.requestFocus()
            return false
        }
        return true
    }
}