package com.example.lomboktravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_check_out.*

class CheckOutActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private  var kendaraanList : ArrayList<Travel> = arrayListOf()
    companion object{
        val EXTRA_POSITION = "extra_position"
        val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null){
            checkoutEmail.setText(user.email)
        }
        val position = intent.getIntExtra(EXTRA_POSITION, 0)

        val tvItemName : TextView = findViewById(R.id.mobilCheckout)
        val tvItemPhoto : ImageView = findViewById(R.id.gambarCheckout)
        val tvItemHarga : TextView = findViewById(R.id.hargaCheckout)
        val tvItemExtra : TextView = findViewById(R.id.extraCheckout)
        val btnPesan : Button = findViewById(R.id.btnChekout)

        kendaraanList.addAll(Kendaraan.listData)
        tvItemName.setText(kendaraanList[position].nama)
        tvItemPhoto.setImageResource(kendaraanList[position].gambar)
        tvItemHarga.setText(kendaraanList[position].harga)
        tvItemExtra.setText(kendaraanList[position].extra)

        btnPesan.setOnClickListener {

            Toast.makeText(applicationContext,"Pesanan anda sedang di Proses", Toast.LENGTH_SHORT).show()
        }
    }
}