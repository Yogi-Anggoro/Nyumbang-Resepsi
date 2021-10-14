package com.example.bukutamusumbanganresepsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.list_kembalikan_sumbangan.view.*

class KembalikanSumbanganAdapter(val tamu: ArrayList<Tamu>?, val context: Context, val onClick: onClickItem): RecyclerView.Adapter<KembalikanSumbanganAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(tamu: Tamu, clickItem: onClickItem){
            view.tv_nomor_ks.text = tamu.no
            view.tv_Nama_ks.text = tamu.nama
            view.tv_alamat_ks.text = tamu.alamat
            view.tv_keterangan_ks.text = tamu.ket
            view.tv_jumlahsumbangan_ks.text = tamu.jumlah_sumbangan
            view.tv_ststussumbangan_ks.text = tamu.Status_sumbangan
            view.tv_pengembalian_ks.text = tamu.pengembalian
            view.card_item_kembalikan.setOnClickListener {
                clickItem.onClickItem(tamu)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KembalikanSumbanganAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_kembalikan_sumbangan,parent,false)
        )

    }

    override fun onBindViewHolder(holder: KembalikanSumbanganAdapter.ViewHolder, position: Int) {
        holder.bind(tamu?.get(position)!!,onClick)

    }

    override fun getItemCount(): Int {
        return tamu?.size!!
    }

    interface onClickItem{
        fun onClickItem(data: Tamu)
    }

}