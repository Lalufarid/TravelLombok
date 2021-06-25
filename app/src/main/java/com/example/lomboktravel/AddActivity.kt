package com.example.lomboktravel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private lateinit var imgWisata : ImageView
    private lateinit var et_namaWisata: EditText
    private lateinit var et_lokasiWisata: EditText
    private lateinit var et_descWisata: EditText
    private lateinit var btn_save: Button
    private lateinit var ref : DatabaseReference
    private lateinit var storage : StorageReference
    private lateinit var firestoreStorage: FirebaseStorage
    private lateinit var imageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        imgWisata = findViewById(R.id.img_wisata)

        et_namaWisata = findViewById(R.id.et_namaWisata)
        et_lokasiWisata = findViewById(R.id.et_lokasiWisata)
        et_descWisata = findViewById(R.id.et_descWisata)
        btn_save = findViewById(R.id.btn_save)

        firestoreStorage = FirebaseStorage.getInstance()
        storage = firestoreStorage.reference


        imgWisata.setOnClickListener {
            val intent = Intent()
            intent.type = "images/"
            intent.action = Intent.ACTION_GET_CONTENT
            uploadImage()
        }

        btn_save.setOnClickListener {
            savedata()
        }
    }

    private fun uploadImage() {
        TODO("Not yet implemented")
    }

    private fun savedata() {
        val gambar = imgWisata
        val nama = et_namaWisata.text.toString().trim()
        val lokasi = et_lokasiWisata.text.toString().trim()
        val desc = et_descWisata.text.toString().trim()

        if (nama.isEmpty()){
            et_namaWisata.error = "masukkan nama"
            return
        }
        if (lokasi.isEmpty()){
            et_lokasiWisata.error = "masukkan lokasi"
            return
        }

        val kabupaten = id_kabupaten.text.toString()
        ref = FirebaseDatabase.getInstance().getReference(kabupaten)

        val wisata = Wisata()
        wisata.nama = nama
        wisata.desc = desc
        wisata.lokasi = lokasi
        saveToFireBase(nama, wisata)


    }

    private fun saveToFireBase(nama : String, wisata: Wisata) {
        ref.child(nama).setValue(wisata).addOnCompleteListener {
            Toast.makeText(applicationContext, "data sudah ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            imageUri = data?.data!!

        }

    }

}