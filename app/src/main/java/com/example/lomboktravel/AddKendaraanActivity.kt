//package com.example.lomboktravel
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.Toast
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
//
//class AddKendaraanActivity : AppCompatActivity() {
//    private lateinit var imgKendaraan :ImageView
//    private lateinit var etKendaraan: EditText
//    private lateinit var etHarga : EditText
//    private lateinit var etExtra : EditText
//    private lateinit var btnSave : Button
//    private lateinit var ref : DatabaseReference
//    private lateinit var storage : StorageReference
//    private lateinit var firestoreStorage: FirebaseStorage
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_kendaraan)
//
//        imgKendaraan = findViewById(R.id.img_kendaraan)
//        etKendaraan = findViewById(R.id.etKendaraan)
//        etHarga = findViewById(R.id.etHarga)
//        etExtra = findViewById(R.id.etExtra)
//        btnSave = findViewById(R.id.btnSave)
//
//        firestoreStorage = FirebaseStorage.getInstance()
//        storage = firestoreStorage.reference
//
//        btnSave.setOnClickListener {
//            saveData()
//        }
//    }
//
//    private fun saveData() {
//        val nama = etKendaraan.text.toString().trim()
//        val harga = etHarga.text.toString().trim()
//        val extra = etExtra.text.toString().trim()
//        ref = FirebaseDatabase.getInstance().getReference("Kendaraan")
//
//        if (nama.isEmpty()){
//            etKendaraan.error = "masukkan nama"
//            return
//        }
//        if (harga.isEmpty()){
//            etHarga.error = "masukkan Harga"
//            return
//        }
//        if (extra.isEmpty()){
//            etExtra.error = "masukkan Harga Extra"
//            return
//        }
//        val kendaraan = Kendaraan()
//        kendaraan.nama = nama
//        kendaraan.harga = harga
//        kendaraan.extra = harga
//
//        saveToFirebase(nama,kendaraan)
//
//
//
//    }
//
//    private fun saveToFirebase(nama: String, kendaraan: Kendaraan) {
//        ref.child(nama).setValue(kendaraan).addOnCompleteListener {
//            Toast.makeText(applicationContext, "data sudah ditambahkan", Toast.LENGTH_SHORT).show()
//        }
//    }
//}