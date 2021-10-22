package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.activity_add_tamu.*
import kotlinx.android.synthetic.main.activity_login.*

class AddTamuActivity : AppCompatActivity(), AddTamuContract.view {
    init {
        AddTamuPresenter(this)
    }

    override lateinit var presenter: AddTamuContract.presenter
    private var loading: AlertDialog? = null
    private val status_sumbangan = "Belum Dikembalikan"
    private val pengembalian_sumbangan = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tamu)
        loading = showDialogLoading()
        presenter.getUsermail()
        btn_addtamu.setOnClickListener {
            if (handleInput()){
                val tamu = Tamu(
                        tv_getemailuser_addtamu.text.toString(),
                        et_nomor_tamu.text.toString(),
                        et_nama_tamu.text.toString(),
                        et_alamat_tamu.text.toString(),
                        et_keterangan_tamu.text.toString(),
                        et_jumlah_sumbangan.text.toString(),
                        status_sumbangan,
                        pengembalian_sumbangan
                )
                presenter.addTamu(tamu)
            }

        }

    }

    override fun onError(message: String) {
        Toast.makeText( this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        et_nomor_tamu.setText("")
        et_nama_tamu.setText("")
        et_alamat_tamu.setText("")
        et_keterangan_tamu.setText("")
        et_jumlah_sumbangan.setText("")
    }

    override fun onProcess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }

    }

    override fun onSuccessMail(email: String) {
        tv_getemailuser_addtamu.text = email
    }

    //isi loading
    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }
    //untuk handle input
    private fun handleInput() : Boolean{
        if (et_nomor_tamu.text.isEmpty()) {
            et_nomor_tamu.error = getString(R.string.fill_data)
            et_nomor_tamu.requestFocus()
            return false
        }
        if (et_nama_tamu.text.isEmpty()) {
            et_nama_tamu.error = getString(R.string.fill_data)
            et_nama_tamu.requestFocus()
            return false
        }
        if (et_alamat_tamu.text.isEmpty()) {
            et_alamat_tamu.error = getString(R.string.fill_data)
            et_alamat_tamu.requestFocus()
            return false
        }
        if (et_keterangan_tamu.text.isEmpty()) {
            et_keterangan_tamu.error = getString(R.string.fill_data)
            et_keterangan_tamu.requestFocus()
            return false
        }
        if (et_jumlah_sumbangan.text.isEmpty()) {
            et_jumlah_sumbangan.error = getString(R.string.fill_data)
            et_jumlah_sumbangan.requestFocus()
            return false
        }
        return true
    }
}