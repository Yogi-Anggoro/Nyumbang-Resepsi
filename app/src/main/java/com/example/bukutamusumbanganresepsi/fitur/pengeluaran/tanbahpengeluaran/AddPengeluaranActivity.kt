package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.tanbahpengeluaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import kotlinx.android.synthetic.main.activity_add_pengeluaran.*
import kotlinx.android.synthetic.main.activity_add_tamu.*

class AddPengeluaranActivity : AppCompatActivity(),AddPengeluaranContract.View {
    override lateinit var presenter : AddPengeluaranContract.Presenter
    init {
        AddPengeluaranPresenter(this)
    }
    var loading : AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pengeluaran)
        loading = showDialogLoading()
        presenter.getUsermail()
        btn_addpengeluaran.setOnClickListener {
            if (handleInput()){
                val pengeluaran = Pengeluaran(
                        et_nomor_pengeluaran_input.text.toString(),
                        et_tanggal_pengeluaran_input.text.toString(),
                        et_jumlah_pengeluaran_input.text.toString(),
                        et_nama_pengeluaran_input.text.toString(),
                        et_keterangan_pengeluaran_input.text.toString(),
                        tv_getemailuser_addpengeluaran.text.toString()
                )
                    presenter.addPengeluaran(pengeluaran)
            }
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onProccess(boolean: Boolean) {
    if (boolean){
        loading?.show()
    }else {
        loading?.dismiss()
    } }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        et_nomor_pengeluaran_input.setText("")
        et_tanggal_pengeluaran_input.setText("")
        et_jumlah_pengeluaran_input.setText("")
        et_nama_pengeluaran_input.setText("")
        et_keterangan_pengeluaran_input.setText("")
        finish()
    }

    override fun onSuccessMail(email: String) {
        tv_getemailuser_addpengeluaran.text = email
    }

    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
    private fun handleInput() : Boolean{
        if (et_nomor_pengeluaran_input.text.isEmpty()) {
            et_nomor_pengeluaran_input.error = getString(R.string.fill_data)
            et_nomor_pengeluaran_input.requestFocus()
            return false
        }
        if (et_tanggal_pengeluaran_input.text.isEmpty()) {
            et_tanggal_pengeluaran_input.error = getString(R.string.fill_data)
            et_tanggal_pengeluaran_input.requestFocus()
            return false
        }
        if (et_jumlah_pengeluaran_input.text.isEmpty()) {
            et_jumlah_pengeluaran_input.error = getString(R.string.fill_data)
            et_jumlah_pengeluaran_input.requestFocus()
            return false
        }
        if (et_nama_pengeluaran_input.text.isEmpty()) {
            et_nama_pengeluaran_input.error = getString(R.string.fill_data)
            et_nama_pengeluaran_input.requestFocus()
            return false
        }
        if (et_keterangan_pengeluaran_input.text.isEmpty()) {
            et_keterangan_pengeluaran_input.error = getString(R.string.fill_data)
            et_keterangan_pengeluaran_input.requestFocus()
            return false
        }
        return true
    }

}