package com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.daftartamu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.adapter.TamuAdapter
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.detailtamu.DetailTamuActivity
import com.example.bukutamusumbanganresepsi.fitur.tamusumbangan.tambahtamu.AddTamuActivity
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.activity_daftar_tamu_.*
import java.util.*
import kotlin.collections.ArrayList

class Daftar_Tamu_Activity : AppCompatActivity(), Daftar_Tamu_Contract.View {

    override lateinit var presenter: Daftar_Tamu_Contract.Presenter
    private var loading : AlertDialog? = null
    private var tamuAdapter : TamuAdapter? = null

    private var listTamu : ArrayList<Tamu >? = null
    private var searchListTamu : ArrayList<Tamu >? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_tamu_)
        Daftar_Tamu_Presnter(this,this)
        loading = showDialogLoading()
        presenter.getTamu()

        btn_addtamu.setOnClickListener {
            startActivity(Intent(this, AddTamuActivity::class.java))
        }
        et_cari_tamu.addTextChangedListener(onSearchChange())
    }

    private  fun onSearchChange() : TextWatcher{
        return object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchListTamu = ArrayList()
                if (s?.isEmpty()!!){
                    searchListTamu = listTamu
                }else{
                    searchListTamu = filtertamu(s.toString())
                }
                val onClickItem = object : TamuAdapter.OnClickItem{
                    override fun onClickItem(data: Tamu) {
                        startActivity(Intent(this@Daftar_Tamu_Activity,DetailTamuActivity::class.java)
                                .putExtra("nomor",data.no)
                        )
                    }

                }
                tamuAdapter = TamuAdapter(searchListTamu, this@Daftar_Tamu_Activity,onClickItem)
                rcv_addtamu.adapter = tamuAdapter
                rcv_addtamu.layoutManager = LinearLayoutManager(this@Daftar_Tamu_Activity,LinearLayoutManager.VERTICAL,false)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }

    }

    private  fun filtertamu(search: String): ArrayList<Tamu>{
        val hasil: ArrayList<Tamu> = ArrayList()
        if (listTamu?.size != null && listTamu?.size != 0)
            for (i in listTamu?.indices!!)
                if (listTamu!!.get(i).nama.toLowerCase(Locale.ROOT).contains(search.toLowerCase(Locale.ROOT))){
                    hasil.add(listTamu!![i])
                }
        return hasil
    }

    override fun onError(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(tamu: ArrayList<Tamu>) {
        listTamu = tamu
        val onClickItem = object : TamuAdapter.OnClickItem{
            override fun onClickItem(data: Tamu) {
                startActivity(Intent(this@Daftar_Tamu_Activity,DetailTamuActivity::class.java)
                        .putExtra("nomor",data.no)
                )
            }

        }
        tamuAdapter = TamuAdapter(tamu, this,onClickItem)
        rcv_addtamu.adapter = tamuAdapter
        rcv_addtamu.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    override fun onProcess(boolean: Boolean) {
        if (boolean){
            loading?.show()
        }else{
            loading?.dismiss()
        }
    }

    //isi loading
    private fun showDialogLoading(): AlertDialog {
        val view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null, false)
        return AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
    }
}