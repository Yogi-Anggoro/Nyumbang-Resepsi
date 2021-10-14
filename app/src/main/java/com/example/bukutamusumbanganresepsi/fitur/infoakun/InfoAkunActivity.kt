package com.example.bukutamusumbanganresepsi.fitur.infoakun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.fitur.main.MainActivity
import com.example.bukutamusumbanganresepsi.model.User
import kotlinx.android.synthetic.main.activity_info_akun.*

class InfoAkunActivity : AppCompatActivity(), InfoAkunContract.view {
    init {
        InfoAkunPresenter(this,this)
    }
    override lateinit var presenter: InfoAkunContract.Presenter
    private var loading: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_akun)
        loading = showDialogLoading()
        presenter.getUser()

        btn_kembali_main.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
    }

    override fun onSucess(user: User) {
        loading?.dismiss()
        tv_email_info.text = user.email
        tv_password_info.text = user.password
        tv_acara_info.text = user.acara
        tv_nama_info.text = user.nama

    }

    override fun onProcess(boolean: Boolean) {
        loading?.show()
    }
    private fun showDialogLoading(): AlertDialog{
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout,null,false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }
}