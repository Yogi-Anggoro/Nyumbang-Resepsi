package com.example.bukutamusumbanganresepsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Pengeluaran
import kotlinx.android.synthetic.main.list_pengeluaran_resepsi.view.*

class PengeluaranAdapter(val pengeluaran : ArrayList<Pengeluaran>?, val context: Context, val onClick: onClickItem ) : RecyclerView.Adapter<PengeluaranAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bind (pengeluaran: Pengeluaran, clickItem: onClickItem){
            view.tv_nomor_pengeluaran.text = pengeluaran.nomor_pengeluaran
            view.tv_tgl_pengeluaran.text = pengeluaran.tanggal_pengeluaran
            view.tv_jumlahPengeluaran.text = pengeluaran.jumlah_pengeluaran
            view.tv_nama_pengeluaran.text = pengeluaran.nama_pengeluaran
            view.tv_keteranganpengeluaran.text = pengeluaran.ket_pengeluaran
            view.card_item_pengeluaran.setOnClickListener {
                clickItem.onClickItem(pengeluaran)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_pengeluaran_resepsi,parent,false))
    }

    override fun onBindViewHolder(holder: PengeluaranAdapter.ViewHolder, position: Int) {
        holder.bind(pengeluaran?.get(position)!!,onClick)
    }

    override fun getItemCount(): Int {
        return pengeluaran?.size!!
    }
    interface onClickItem {
        fun onClickItem (data: Pengeluaran)
    }
}