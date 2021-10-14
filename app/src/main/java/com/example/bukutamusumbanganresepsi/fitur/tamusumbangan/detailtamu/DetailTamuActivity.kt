package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.detailtamu

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu.AddTamuContract
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.activity_add_tamu.*
import kotlinx.android.synthetic.main.activity_detail_tamu.*

class DetailTamuActivity : AppCompatActivity(), DetailTamuContract.View{
    override lateinit var presenter: DetailTamuContract.Presenter
    private var loading: AlertDialog? = null
    init {
        DetailTamuPresenter(this)
    }
    var tamu: Tamu? = null
    var nomor : String? = null
    var idTamu : String? = null
    private val status_sumbangan = "Belum Dikembalikan"
    private val pengembalian_sumbangan = "-"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tamu)
        loading = showDialogLoading()
        nomor = intent.getStringExtra("nomor")
        presenter.getTamu(nomor)

        btn_update_tamu.setOnClickListener {
            if (handleInput()){
                val tamu = Tamu(
                        et_d_nomor_tamu.text.toString(),
                        et_d_nama_tamu.text.toString(),
                        et_d_alamat_tamu.text.toString(),
                        et_d_keterangan_tamu.text.toString(),
                        et_d_jumlah_sumbangan.text.toString(),
                        status_sumbangan,pengembalian_sumbangan

                )
                presenter.updateTamu(idTamu!!, tamu)
            }
        }
        btn_delete_tamu.setOnClickListener {
            presenter.deleteTamu(idTamu!!)
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetData(tamu: Tamu, no: String?) {
        idTamu = no
        et_d_nomor_tamu.setText(tamu?.no)
        et_d_nama_tamu.setText(tamu?.nama)
        et_d_alamat_tamu.setText(tamu?.alamat)
        et_d_keterangan_tamu.setText(tamu?.ket)
        et_d_jumlah_sumbangan.setText(tamu?.jumlah_sumbangan)
    }

    override fun onSucessDelete(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    //loading
    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
    //untuk handle input
    private fun handleInput() : Boolean{
        if (et_d_nomor_tamu.text.isEmpty()) {
            et_d_nomor_tamu.error = getString(R.string.fill_data)
            et_d_nomor_tamu.requestFocus()
            return false
        }
        if (et_d_nama_tamu.text.isEmpty()) {
            et_d_nama_tamu.error = getString(R.string.fill_data)
            et_d_nama_tamu.requestFocus()
            return false
        }
        if (et_d_alamat_tamu.text.isEmpty()) {
            et_d_alamat_tamu.error = getString(R.string.fill_data)
            et_d_alamat_tamu.requestFocus()
            return false
        }
        if (et_d_keterangan_tamu.text.isEmpty()) {
            et_d_keterangan_tamu.error = getString(R.string.fill_data)
            et_d_keterangan_tamu.requestFocus()
            return false
        }
        if (et_d_jumlah_sumbangan.text.isEmpty()) {
            et_d_jumlah_sumbangan.error = getString(R.string.fill_data)
            et_d_jumlah_sumbangan.requestFocus()
            return false
        }
        return true
    }
}