package com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.daftarkembalikansumbangan

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
import com.example.bukutamusumbanganresepsi.adapter.KembalikanSumbanganAdapter
import com.example.bukutamusumbanganresepsi.fitur.kembalikansumbangan.detailkembalikansumbangan.DetailKembalikanSumbanganActivity
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.activity_daftar_kembalikan_sumbangan.*
import java.util.*
import kotlin.collections.ArrayList

class DaftarKembalikanSumbanganActivity : AppCompatActivity(), DaftarKembalikanSumbanganContract.View {
    override  lateinit var presenter: DaftarKembalikanSumbanganContract.Presenter
    private var loading : AlertDialog? = null
    private var DKSAdapter : KembalikanSumbanganAdapter? = null
    private var listTamu : ArrayList<Tamu>? = null
    private var searchListTamu : ArrayList<Tamu>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_kembalikan_sumbangan)
        DaftarKembalikanSumbanganPresenter(this,this)
        loading = showDialogLoading()
        presenter.getTamu()
        et_cari_tamu_ks.addTextChangedListener(onSearchChange())
    }

    override fun onErorr(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onProccess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    private fun onSearchChange() : TextWatcher{
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchListTamu = ArrayList()
                if (s?.isEmpty()!!){
                    searchListTamu = listTamu
                }else {
                    searchListTamu = filterTamu(s.toString())
                }
                val  onClickItem = object : KembalikanSumbanganAdapter.onClickItem{
                    override fun onClickItem(data: Tamu) {
                        startActivity(Intent(this@DaftarKembalikanSumbanganActivity,DetailKembalikanSumbanganActivity::class.java)
                                .putExtra("nomor",data.no))
                    }

                }
                DKSAdapter = KembalikanSumbanganAdapter(searchListTamu,this@DaftarKembalikanSumbanganActivity,onClickItem)
                rcv_kembalikansumbangan.adapter = DKSAdapter
                rcv_kembalikansumbangan.layoutManager = LinearLayoutManager(this@DaftarKembalikanSumbanganActivity,LinearLayoutManager.VERTICAL,false)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
    }

    private fun filterTamu(search: String) : ArrayList<Tamu> {
        val result: ArrayList<Tamu> = ArrayList()
        if (listTamu?.size != null && listTamu?.size != 0) {
            for (i in listTamu?.indices!!) {
                if (listTamu!!.get(i).nama.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT))){
                    result.add(listTamu!![i])
                }
            }
        }
        return result
    }

    override fun onSuccess(tamu: ArrayList<Tamu>) {
        listTamu = tamu
        val  onClickItem = object : KembalikanSumbanganAdapter.onClickItem{
            override fun onClickItem(data: Tamu) {
                startActivity(Intent(this@DaftarKembalikanSumbanganActivity,DetailKembalikanSumbanganActivity::class.java)
                        .putExtra("nomor",data.no))
            }

        }
        DKSAdapter= KembalikanSumbanganAdapter(tamu,this,onClickItem)
        rcv_kembalikansumbangan.adapter = DKSAdapter
        rcv_kembalikansumbangan.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }


    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
}