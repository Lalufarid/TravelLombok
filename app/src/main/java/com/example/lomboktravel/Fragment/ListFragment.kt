package com.example.lomboktravel.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lomboktravel.*
import com.example.lomboktravel.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var rcView : RecyclerView
    private lateinit var listWisata : ArrayList<Wisata>

    private val list = ArrayList<Wisata>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcView = view.findViewById(R.id.rv_listWisata)
        rcView.setHasFixedSize(true)
        rcView.layoutManager = LinearLayoutManager(view.context)

        val wisataAdapter = WisataAdapter(list)

        listWisata = arrayListOf<Wisata>()
        getWisataData()

        wisataAdapter.setOnItemClickCallback(object :
            WisataAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Wisata) {
                val wisataFragment =
                    WisataFragment()
                val bundle = Bundle()

                }

        })

        btnNext.setOnClickListener {
            startActivity(Intent(requireContext(), AdminActivity::class.java))
        }

    }

    private fun moveActivity() {

    }
    private fun getWisataData() {
        dbref = FirebaseDatabase.getInstance().getReference("wisata")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    listWisata.clear()
                    for (userSnapshot in snapshot.children){
                        val wst = userSnapshot.getValue(Wisata::class.java)

                        listWisata.add(wst!!)
                    }
                    rcView.adapter =
                        WisataAdapter(listWisata)
                }
            }

        })
    }


}