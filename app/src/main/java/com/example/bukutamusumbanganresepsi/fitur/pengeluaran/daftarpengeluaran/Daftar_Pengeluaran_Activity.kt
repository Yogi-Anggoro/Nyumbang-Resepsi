package com.example.bukutamusumbanganresepsi.fitur.pengeluaran.daftarpengeluaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.adapter.PengeluaranAdapter
import com.example.bukutamusumbanganresepsi.fitur.pengeluaran.detailpengeluaran.DetailPengeluaranActivity
import com.example.bukutamusumbanganresepsi.fitur.pengeluaran.tanbahpengeluaran.AddPengeluaranActivity
import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import kotlinx.android.synthetic.main.activity_daftar_pengeluaran_.*
import java.util.*
import kotlin.collections.ArrayList

class Daftar_Pengeluaran_Activity : AppCompatActivity(),Daftar_Pengeluaran_Contract.View {

    override lateinit var presenter : Daftar_Pengeluaran_Contract.Presenter
    var loading : AlertDialog? = null
    var pengeluaranAdapter : PengeluaranAdapter? = null

    private var listPengeluaran : ArrayList<Pengeluaran>? = null
    private var searchlistPengeluaran : ArrayList<Pengeluaran>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_pengeluaran_)
        Daftar_Pengeluaran_Presenter(this)
        loading = showDialogLoading()
        presenter.getPengeluaran()
        presenter.getJumlah()
        presenter.getJumlahSumbangan()
        et_cari_Pengeluaran.addTextChangedListener(onSearchChange())

        btn_addpengeluaran.setOnClickListener {
            val intent = Intent(this,AddPengeluaranActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onSearchChange() : TextWatcher{
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchlistPengeluaran = ArrayList()
                if (s?.isEmpty()!!){
                    searchlistPengeluaran = listPengeluaran
                }else{
                    searchlistPengeluaran = filterpengeluaran(s.toString())
                }
                val onClickItem = object : PengeluaranAdapter.onClickItem {
                    override fun onClickItem(data: Pengeluaran) {
                        startActivity(Intent(this@Daftar_Pengeluaran_Activity, DetailPengeluaranActivity::class.java)
                            .putExtra("nomor",data.nomor_pengeluaran)
                        )
                    }
                }
                pengeluaranAdapter = PengeluaranAdapter(searchlistPengeluaran,this@Daftar_Pengeluaran_Activity,onClickItem)
                rv_pengeluaran_daf.adapter = pengeluaranAdapter
                rv_pengeluaran_daf.layoutManager = LinearLayoutManager(this@Daftar_Pengeluaran_Activity,LinearLayoutManager.VERTICAL,false)

            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
    }
    private fun filterpengeluaran(search: String) : ArrayList<Pengeluaran>{
        val result: ArrayList<Pengeluaran> = ArrayList()
        if (listPengeluaran?.size != null && listPengeluaran?.size != 0) {
            for (i in listPengeluaran?.indices!!) {
                if (listPengeluaran!!.get(i).nama_pengeluaran.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT)))
                    result.add(listPengeluaran!![i])
            }
        }
        return result
    }
    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onProccess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else {
            loading?.dismiss()
        }
    }

    override fun onSuccess(pengeluaran: ArrayList<Pengeluaran>) {
        listPengeluaran = pengeluaran
        val onClickItem = object : PengeluaranAdapter.onClickItem {
            override fun onClickItem(data: Pengeluaran) {
                startActivity(Intent(this@Daftar_Pengeluaran_Activity, DetailPengeluaranActivity::class.java)
                        .putExtra("nomor",data.nomor_pengeluaran)
                )
            }
        }

        pengeluaranAdapter = PengeluaranAdapter(pengeluaran,this,onClickItem)
        rv_pengeluaran_daf.adapter = pengeluaranAdapter
        rv_pengeluaran_daf.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    override fun onSuccessJumlah(jumlah: Int) {
       jumdafpeng.text = jumlah.toString()
    }

    override fun onSuccessJumlahSumbangan(jumlahs: Int) {
        jumpengpel.text = jumlahs.toString()
    }

    fun showDialogLoading(): AlertDialog{
    val view = LayoutInflater.from(this).inflate(R.layout.loading_layout,null,false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
}