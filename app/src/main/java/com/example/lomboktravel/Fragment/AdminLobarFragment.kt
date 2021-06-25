package com.example.lomboktravel.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lomboktravel.*
import com.example.lomboktravel.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_admin_lobar.*

class AdminLobarFragment : Fragment(), View.OnClickListener {
    private lateinit var dbref : DatabaseReference
    private lateinit var rcView : RecyclerView
    private lateinit var listWisata : ArrayList<Wisata>


    private lateinit var list : MutableList<Wisata>
    private lateinit var listview : ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_lobar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcView = view.findViewById(R.id.rv_adminlist)
        rcView.setHasFixedSize(true)

        rcView.layoutManager = LinearLayoutManager(view.context)
        dbref = FirebaseDatabase.getInstance().getReference("Lombok Barat")
//        listview = view.findViewById(R.id.lv_adminlist)

//        val wisataAdapter = WisataAdapter(list)
        listWisata = arrayListOf<Wisata>()
        getData()

//        list = mutableListOf()
//
//        dbref.addValueEventListener(object  : ValueEventListener{
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//
//                if (p0.exists()){
//                    list.clear()
//                    for (h in p0.children){
//                        val wisata = h.getValue(Wisata::class.java)
//                        if (wisata != null) {
//                            list.add(wisata)
//                        }
//                    }
//
//                    val adapter =  AdminAdapter(view.context, R.layout.item_admin, list)
//                    listview.adapter = adapter
//                }
//
//            }
//
//        })

        addWisata.setOnClickListener(this)
    }

    private fun getData() {
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("LobarFragment", error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    listWisata.clear()
                    for (userSnapshot in snapshot.children){
                        val wst = userSnapshot.getValue(Wisata::class.java)

                        listWisata.add(wst!!)
                    }
                    rcView.adapter = WisataAdapter(listWisata)
                }
            }

        })
    }

    override fun onClick(v: View?) {
        startActivity(Intent(requireContext(),AddActivity::class.java))
    }


}