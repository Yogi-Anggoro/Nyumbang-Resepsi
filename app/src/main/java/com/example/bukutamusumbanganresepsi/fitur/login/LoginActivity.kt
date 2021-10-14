package com.example.bukutamusumbanganresepsi.fitur.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.fitur.main.MainActivity
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.fitur.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    override lateinit var presenter: LoginContract.Presenter
    //private var loading: ProgressDialog? = null
    private var loading: AlertDialog? = null
    init {
        LoginPresenter(this,this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loading = showDialogLoading()
        btn_login.setOnClickListener {
            if (handleInput())
            presenter.login(et_email_login.text.toString(),et_password_login.text.toString())
        }



        //blm punya akun
        no_akun.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
    }

    override fun onSucess(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
        loading?.dismiss()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onProcess(boolean: Boolean) {
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

//untuk handle input
    private fun handleInput() : Boolean{
        if (et_email_login.text.isEmpty()) {
            et_email_login.error = getString(R.string.fill_data)
            et_email_login.requestFocus()
            return false
        }
        if (et_password_login.text.isEmpty()) {
            et_password_login.error = getString(R.string.fill_data)
            et_password_login.requestFocus()
            return false
        }
        if (et_password_login.text.length < 6){
            et_password_login.error = getString(R.string.fill_pasword)
            et_password_login.requestFocus()
        }
        return true
    }
}