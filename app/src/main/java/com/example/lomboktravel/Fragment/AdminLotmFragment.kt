package com.example.lomboktravel.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lomboktravel.AddActivity
import com.example.lomboktravel.R
import com.example.lomboktravel.Wisata
import com.example.lomboktravel.WisataAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_admin_lotm.*

class AdminLotmFragment : Fragment(), View.OnClickListener {
    private lateinit var dbref : DatabaseReference
    private lateinit var rcView : RecyclerView
    private lateinit var listWisata : ArrayList<Wisata>

    private val list = ArrayList<Wisata>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_lotm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcView = view.findViewById(R.id.rv_adminlistlotim)
        rcView.setHasFixedSize(true)

        rcView.layoutManager = LinearLayoutManager(view.context)
        dbref = FirebaseDatabase.getInstance().getReference("Lombok Timur")

        val wisataAdapter = WisataAdapter(list)
        listWisata = arrayListOf<Wisata>()
        getData()
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
        startActivity(Intent(requireContext(), AddActivity::class.java))
    }


}