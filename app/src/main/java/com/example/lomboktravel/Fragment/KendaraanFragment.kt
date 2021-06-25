package com.example.lomboktravel.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lomboktravel.*
import com.example.lomboktravel.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_kendaraan.*

class KendaraanFragment : Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var rcKendaraan: RecyclerView
    private  var kendaraanList : ArrayList<Travel> = arrayListOf()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kendaraan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        dbref = FirebaseDatabase.getInstance().getReference("Kendaraan")


        rcKendaraan = view.findViewById(R.id.rc_kendaraan)
        rcKendaraan.setHasFixedSize(true)
        kendaraanList.addAll(Kendaraan.listData)
        rcKendaraan.layoutManager = LinearLayoutManager(getContext())
        val listDesAdapter = KendaraanAdapter(kendaraanList)
        rcKendaraan.adapter = listDesAdapter

//
//        addKendaraan.setOnClickListener {
//            startActivity(Intent(requireContext(), AddKendaraanActivity::class.java))
//        }

//        btnPesan = view.findViewById(R.id.btnPesan)
//        btnPesan1 = view.findViewById(R.id.btnPesan1)
//        btnPesan2 = view.findViewById(R.id.btnPesan2)
//
//        btnPesan.setOnClickListener {
//            val checkOutFragment = CheckOutFragment()
//            val nama = "16"
//            val harga = "599.999"
//            val extra = "100.000"
//            val bundle = Bundle()
//            bundle.putString(CheckOutFragment., kendaraanList)
//            fragmentManager?.beginTransaction()?.apply {
//                replace(R.id.nav_host_fragment, checkOutFragment,CheckOutFragment::class.java.simpleName)
//                    .addToBackStack(null)
//                    .commit()
//
//            }
//        }



    }

//    private fun getWisataData() {
//        dbref.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("KendaraanFragment", error.message)
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    kendaraanList.clear()
//                    for (userSnapshot in snapshot.children){
//                        val wst = userSnapshot.getValue(Kendaraan::class.java)
//                        kendaraanList.add(wst!!)
//                    }
//                  rcKendaraan.adapter = KendaraanAdapter(kendaraanList)
//                }
//            }
//
//        })
//    }

}
