package com.example.lomboktravel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lomboktravel.Fragment.EditFragment
import kotlinx.android.synthetic.main.item_wisata.view.*

class WisataAdapter(private val wisataList: ArrayList<Wisata>):
    RecyclerView.Adapter<WisataAdapter.WisataViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class WisataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(wisata: Wisata){
            with(itemView){
                val tv_nama : TextView = findViewById(R.id.tv_wisata)
                val tv_lokasi : TextView = findViewById(R.id.tv_lokasi)
                tv_nama.text = wisata.nama
                tv_lokasi.text = wisata.lokasi
                btnEdit.setOnClickListener {
                    val editFragment = EditFragment()

                }
                tv_nama.setOnClickListener {
                    val context = it.context
                    val catDetailIntent = Intent(context, WisataActivity::class.java)
                    catDetailIntent.putExtra(WisataActivity.EXTRA_POSITION, position)
                    context.startActivity(catDetailIntent)
                }
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(wisata) }
            }
        }

        private fun editWisata() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        return WisataViewHolder(view)
    }
    

    override fun getItemCount(): Int = wisataList.size

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        holder.bind(wisataList[position])

    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Wisata)
    }

}