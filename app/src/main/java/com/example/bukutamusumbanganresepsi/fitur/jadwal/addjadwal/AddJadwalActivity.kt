package com.example.bukutamusumbanganresepsi.fitur.jadwal.addjadwal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Jadwal
import kotlinx.android.synthetic.main.activity_add_jadwal.*
import kotlinx.android.synthetic.main.activity_add_pengeluaran.*

class AddJadwalActivity : AppCompatActivity(),AddJadwalContract.View {
    override lateinit var presenter: AddJadwalContract.Presenter
    init {
        AddJadwalPresenter(this)
    }
    var loading: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_jadwal)
        loading = showDialogLoading()
        presenter.getUserMail()
        btn_addjadwal_in.setOnClickListener {
            if (handleInput()){
                val jadwal = Jadwal(
                    tv_getemailuser_addjadwal.text.toString(),
                    et_acara_jadwal_add.text.toString(),
                    et_nama_jadwal_add.text.toString(),
                    et_alamat_jadwal_add.text.toString(),
                    et_tanggal_jadwal_add.text.toString()
                )
                presenter.addJadwal(jadwal)
            }

        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onProccess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        et_acara_jadwal_add.setText("")
        et_nama_jadwal_add.setText("")
        et_alamat_jadwal_add.setText("")
        et_tanggal_jadwal_add.setText("")
        finish()
    }

    override fun onSuccessMail(email: String) {
        tv_getemailuser_addjadwal.text = email
    }

    private fun showDialogLoading(): AlertDialog{
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout,null,false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }

    private fun handleInput() : Boolean{
        if (et_acara_jadwal_add.text.isEmpty()) {
            et_acara_jadwal_add.error = getString(R.string.fill_data)
            et_acara_jadwal_add.requestFocus()
            return false
        }
        if (et_nama_jadwal_add.text.isEmpty()) {
            et_nama_jadwal_add.error = getString(R.string.fill_data)
            et_nama_jadwal_add.requestFocus()
            return false
        }
        if (et_alamat_jadwal_add.text.isEmpty()) {
            et_alamat_jadwal_add.error = getString(R.string.fill_data)
            et_alamat_jadwal_add.requestFocus()
            return false
        }
        if (et_tanggal_jadwal_add.text.isEmpty()) {
            et_tanggal_jadwal_add.error = getString(R.string.fill_data)
            et_tanggal_jadwal_add.requestFocus()
            return false
        }
        return true
    }

}