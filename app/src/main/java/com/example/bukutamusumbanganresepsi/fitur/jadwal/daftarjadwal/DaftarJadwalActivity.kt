package com.example.bukutamusumbanganresepsi.fitur.jadwal.daftarjadwal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.adapter.JadwalAdapter
import com.example.bukutamusumbanganresepsi.fitur.jadwal.addjadwal.AddJadwalActivity
import com.example.bukutamusumbanganresepsi.fitur.jadwal.detailjadwal.DetailJadwalActivity
import com.example.bukutamusumbanganresepsi.model.Jadwal
import kotlinx.android.synthetic.main.activity_daftar_jadwal.*

class DaftarJadwalActivity : AppCompatActivity(), DaftarJadwalContract.View {
    override lateinit var presenter: DaftarJadwalContract.Presenter
    private var loading: AlertDialog? = null
    private var jadwalAdapter: JadwalAdapter? = null
    private var listJadwal: ArrayList<Jadwal>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_jadwal)
        DaftarJadwalPresenter(this)
        loading = showDialogLoading()
        presenter.getJadwal()
        presenter.getJumlah()
        btn_addjadwal_daftar.setOnClickListener {
            val intent = Intent(this, AddJadwalActivity::class.java)
            startActivity(intent)
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

    override fun onSuccess(jadwal: ArrayList<Jadwal>) {
       listJadwal = jadwal
        val onClickItem = object : JadwalAdapter.OnClickItem{
            override fun OnClickItem(data: Jadwal) {
                startActivity(Intent(this@DaftarJadwalActivity,DetailJadwalActivity::class.java)
                    .putExtra("nama",data.nama)
                )
            }

        }
        jadwalAdapter = JadwalAdapter(jadwal, this,onClickItem)
        rcv_addjadwal.adapter = jadwalAdapter
        rcv_addjadwal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onSuccessJumlah(jumlah: Int) {
        tv_jumjad.text = jumlah.toString()
    }

    private fun showDialogLoading(): AlertDialog{
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout,null,false)
        return AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
    }


}