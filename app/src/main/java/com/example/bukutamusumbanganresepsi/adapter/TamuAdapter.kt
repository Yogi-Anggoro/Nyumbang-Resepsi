package com.example.bukutamusumbanganresepsi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bukutamusumbanganresepsi.R
import com.example.bukutamusumbanganresepsi.model.Tamu
import kotlinx.android.synthetic.main.list_tamu_sumbangan.view.*

class TamuAdapter(val tamu: ArrayList<Tamu>?, val context: Context, val onClick: OnClickItem) : RecyclerView.Adapter<TamuAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        fun bind(tamu: Tamu, clickItem: OnClickItem){
            view.tv_nomor.text = tamu.no
            view.tv_Nama.text = tamu.nama
            view.tv_alamat.text = tamu.alamat
            view.tv_keterangan.text = tamu.ket
            view.tv_jumlahsumbangan_in.text = tamu.jumlah_sumbangan
            view.card_item_add.setOnClickListener {
                clickItem.onClickItem(tamu)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TamuAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_tamu_sumbangan,parent,false)
        )
    }

    override fun onBindViewHolder(holder: TamuAdapter.ViewHolder, position: Int) {
        holder.bind(tamu?.get(position)!!,onClick)
    }

    override fun getItemCount(): Int {
        return tamu?.size!!
    }

    //fungsi untuk on click
    interface OnClickItem{
        fun onClickItem(data: Tamu)
    }
}