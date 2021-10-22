package com.example.bukutamusumbanganresepsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Jadwal
import kotlinx.android.synthetic.main.list_jadwal.view.*

class JadwalAdapter(val item: ArrayList<Jadwal>,val context: Context, val onClick: OnClickItem): RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view= view
        fun bind(item: Jadwal,clickItem: OnClickItem){
            view.tv_acara_daftar.text = item.acara
            view.tv_Nama_daftar.text = item.nama
            view.tv_alamat_daftar.text = item.alamat
            view.tv_tanggal_daftar.text = item.tanggal
            view.card_item_daftar_jadwal.setOnClickListener {
                clickItem.OnClickItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_jadwal,parent,false)
        )
    }

    override fun onBindViewHolder(holder: JadwalAdapter.ViewHolder, position: Int) {
        holder.bind(item?.get(position)!!,onClick)
    }

    override fun getItemCount(): Int {
        return item?.size!!
    }
    interface OnClickItem {
        fun OnClickItem(data: Jadwal)
    }
}