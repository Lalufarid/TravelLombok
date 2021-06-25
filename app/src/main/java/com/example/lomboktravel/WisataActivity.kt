package com.example.lomboktravel

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_wisata.*

class WisataActivity : AppCompatActivity() {


    private  var listWisata : ArrayList<Wisata> = arrayListOf()
    private lateinit var auth: FirebaseAuth


    companion object{
        var EXTRA_POSITION = "extra_posision"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wisata)

        val wisata = Wisata()

        val position = intent.getIntExtra(EXTRA_POSITION, 0)
        listWisata.addAll(listOf(wisata))

        tvWIsataClicked.setText(listWisata[position].nama)

//        tvItemName.setText(desList[position].nama)
//        tvItemDetail.setText(desList[position].deskripsi)
    }




}