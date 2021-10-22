package com.example.bukutamusumbanganresepsi.fitur.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.fitur.about.AboutActivity
import com.example.bukutamusumbanganresepsi.fitur.infoakun.InfoAkunActivity
import com.example.bukutamusumbanganresepsi.fitur.jadwal.addjadwal.AddJadwalActivity
import com.example.bukutamusumbanganresepsi.fitur.jadwal.daftarjadwal.DaftarJadwalActivity
import com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.daftarkembalikansumbangan.DaftarKembalikanSumbanganActivity
import com.example.bukutamusumbanganresepsi.fitur.login.LoginActivity
import com.example.bukutamusumbanganresepsi.fitur.pengeluaran.daftarpengeluaran.Daftar_Pengeluaran_Activity
import com.example.bukutamusumbanganresepsi.fitur.rekap.RekapDataActivity
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.daftartamu.Daftar_Tamu_Activity
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.detailtamu.DetailTamuActivity
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu.AddTamuActivity
import com.example.bukutamusumbanganresepsi.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainContract.View {
    override lateinit var presenter: MainContract.Presenter
    private var loading: AlertDialog? = null
    init {
        MainPresenter(this,this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading = showDialogLoading()
        presenter.getUser()

        //action Button atas
        btn_info_akun.setOnClickListener {
            val intent = Intent(this,InfoAkunActivity::class.java)
            startActivity(intent)
        }
        btn_about.setOnClickListener {
            val intent = Intent(this,AboutActivity::class.java)
            startActivity(intent)
        }

        //action Button
        crd_tambah_tamu.setOnClickListener{
            val intent = Intent(this,Daftar_Tamu_Activity::class.java)
            startActivity(intent)
        }
        crd_kembalikan_sumbangan.setOnClickListener {
            val intent = Intent(this,DaftarKembalikanSumbanganActivity::class.java)
            startActivity(intent)
        }
        crd_jadwal_nyumbang.setOnClickListener {
            val intent = Intent(this,DaftarJadwalActivity::class.java)
            startActivity(intent)
        }
        crd_pengeluaran.setOnClickListener {
            val intent = Intent(this,Daftar_Pengeluaran_Activity::class.java)
            startActivity(intent)
        }
        crd_rekap.setOnClickListener {
            val intent = Intent(this,RekapDataActivity::class.java)
            startActivity(intent)
        }
        crd_exit.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
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

    override fun onSucess(acara: String, nama: String) {
        dalam_rangka.text = acara
        orangnya.text = nama
    }

//    override fun onSucess(user: User) {
//        dalam_rangka.text = user.acara
//        orangnya.text = user.nama
//    }

    private fun showDialogLoading(): AlertDialog{
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout,null,false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
}