package com.example.lomboktravel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_kendaraan.view.*

class KendaraanAdapter (private val kendaraanList : ArrayList<Travel>) : RecyclerView.Adapter<KendaraanAdapter.KendaraanViewHolder>() {

    inner class KendaraanViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(travel: Travel){
            with(itemView){
                val tvKendaraan = findViewById<TextView>(R.id.tvKendaraan)
                val tvHarga : TextView = findViewById(R.id.tvHarga)
                val tvExxtra : TextView = findViewById((R.id.tvExtra))
                val gambar : ImageView = findViewById(R.id.gambarKendaraan)
                val btnpesan : Button = findViewById(R.id.btnPesan)

                tvKendaraan.text = travel.nama
                tvHarga.text = travel.harga
                tvExxtra.text = travel.extra
                Glide.with(itemView.context)
                        .load(travel.gambar)
                        .apply(RequestOptions().override(55, 55))
                        .into(gambar)
                btnpesan.setOnClickListener {
                    val context = it.context
                    val catDetailIntent = Intent(context, CheckOutActivity::class.java)
                    catDetailIntent.putExtra(CheckOutActivity.EXTRA_POSITION, position)
                    context.startActivity(catDetailIntent)
                }


            }
        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KendaraanViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_kendaraan, parent, false)
        return KendaraanViewHolder(view)
    }

    override fun getItemCount(): Int = kendaraanList.size

    override fun onBindViewHolder(holder: KendaraanViewHolder, position: Int) {
        holder.bind(kendaraanList[position])
    }
}