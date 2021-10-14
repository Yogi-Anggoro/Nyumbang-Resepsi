package com.example.bukutamusumbanganresepsi.fitur.register

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.fitur.login.LoginActivity
import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import com.example.bukutamusumbanganresepsi.model.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.view {

    override lateinit var presenter: RegisterContract.Presenter
    //private var loading: ProgressDialog? = null
    private var loading: AlertDialog? = null

    init {
        RegisterPresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        loading = showDialogLoading()
        btn_register.setOnClickListener {
            if (handleInput()) {
                val user = User(
                    et_email_register.text.toString(),
                    et_password_register.text.toString(),
                    et_event_register.text.toString(),
                    et_nama_register.text.toString(),
                )
                presenter.register(user)
            }
        }

        //dah ada akun
        have_akun.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this ,message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this ,message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
    }

    override fun onProcess(message: Boolean) {
        loading?.show()
    }


    //untuk isi loading
    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout,null,false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }


    // handle input
    private fun handleInput() : Boolean{
        if (et_email_register.text.isEmpty()){
            et_email_register.error = getString(R.string.fill_data)
            et_email_register.requestFocus()
            return false
        }
        if (et_password_register.text.isEmpty()) {
            et_password_register.error = getString(R.string.fill_data)
            et_password_register.requestFocus()
            return false
        }
        if (et_password_register.text.length < 6) {
            et_password_register.error = getString(R.string.fill_pasword)
            et_password_register.requestFocus()
            return false
        }
        if (et_event_register.text.isEmpty()) {
            et_event_register.error = getString(R.string.fill_data)
            et_event_register.requestFocus()
            return false
        }
        if (et_nama_register.text.isEmpty()) {
            et_nama_register.error = getString(R.string.fill_data)
            et_nama_register.requestFocus()
            return false
        }
        return true
    }
}