package com.example.lomboktravel.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lomboktravel.R
import com.example.lomboktravel.Wisata
import com.example.lomboktravel.WisataAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_lobar_wst.*
import kotlinx.android.synthetic.main.fragment_lobar_wst.tvLobar
import kotlinx.android.synthetic.main.fragment_lobar_wst.tvLobut
import kotlinx.android.synthetic.main.fragment_lobar_wst.tvLoteng
import kotlinx.android.synthetic.main.fragment_lobar_wst.tvLotim
import kotlinx.android.synthetic.main.fragment_loteng_wst.*

class LotengWstFragment : Fragment() {
//    private lateinit var database : DatabaseReference
//    private lateinit var wisataAdapter: WisataAdapter
//    private val dataWisata = ArrayList<Wisata>()
    private lateinit var dbref : DatabaseReference
    private lateinit var rcView : RecyclerView
    private lateinit var listWisata : ArrayList<Wisata>

    private val list = ArrayList<Wisata>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loteng_wst, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)
        rcView = view.findViewById(R.id.rv_listWisata)
        rcView.setHasFixedSize(true)
        rcView.layoutManager = LinearLayoutManager(view.context)
        dbref = FirebaseDatabase.getInstance().getReference("Lombok Tengah")
        val wisataAdapter = WisataAdapter(list)

        listWisata = arrayListOf<Wisata>()
        getData()

        tvLobar.setOnClickListener{
            toLobar()
        }
        tv_lobut.setOnClickListener{
            toLobut()
        }
        tv_loteng.setOnClickListener{
            toLoteng()
        }
        tv_lotim.setOnClickListener{
            toLotim()
        }
        btn_next.setOnClickListener{
            val kendaraanFragment = KendaraanFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.nav_host_fragment,kendaraanFragment, KendaraanFragment::class.java.simpleName)
                        .addToBackStack(null)
                        .commit()
            }
        }



//        database = FirebaseDatabase.getInstance().getReference("Lombok Tengah")
//        getData()
//        rv_listWisata.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun toLotim() {
        val lotimWstFragment = LotimWstFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.nav_host_fragment,lotimWstFragment, LotimWstFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
        }


    }

    private fun toLoteng() {
        val lotengWstFragment = LotengWstFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.nav_host_fragment,lotengWstFragment, LotengWstFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
        }

    }

    private fun toLobut() {
        val lobutWstFragment = LobutWstFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.nav_host_fragment,lobutWstFragment,LobutWstFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun toLobar() {
        val lobarWstFragment = LobarWstFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.nav_host_fragment,lobarWstFragment, LobarWstFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun getData() {

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("LotengFragment", error.message)
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

//        database.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("LobarFragment", error.message)
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (data in snapshot.children){
//                    var loteng = data.getValue(Wisata::class.java)
//                    Log.d("LotengFragment", "OnSuccess : $loteng")
//                    dataWisata.clear()
//                    dataWisata?.add(loteng!!)
//
//                    wisataAdapter = WisataAdapter(dataWisata!!)
//                    rv_listWisata.setHasFixedSize(true)
//                    rv_listWisata.adapter = wisataAdapter
//                }
//            }
//        })
    }

}