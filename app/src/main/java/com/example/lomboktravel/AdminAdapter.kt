package com.example.lomboktravel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdminAdapter (val mCtx : Context, val layoutResId : Int, val wstList : List<Wisata>) : ArrayAdapter<Wisata>(mCtx,layoutResId,wstList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)

        val view : View = layoutInflater.inflate(layoutResId, null)

        val tvNama : TextView = view.findViewById(R.id.tvWstAdmin)
        val tvLokasi : TextView = view.findViewById(R.id.tvLksAdmin)

        val wisata: Wisata = wstList[position]

        tvNama.text = wisata.nama
        tvLokasi.text = wisata.lokasi

        tvNama.setOnClickListener {
            val context = it.context
            val catDetailIntent = Intent(context, WisataActivity::class.java)
            catDetailIntent.putExtra(WisataActivity.EXTRA_POSITION, position)
            context.startActivity(catDetailIntent)
        }

        return view

    }
}